package com.ming.shiro.service.security;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Maps;
import com.ming.shiro.dao.security.UserDao;
import com.ming.shiro.domain.security.Role;
import com.ming.shiro.domain.security.User;
import com.ming.shiro.utils.ToolUtil;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Map;
import java.util.Set;

/**
 * 登录账号 Service 实现类
 * <p>
 * ///////////////////////////////////////////////////
 * //                    _ooOoo_                    //
 * //                   o8888888o                   //
 * //                   88" . "88                   //
 * //                   (| -_- |)                   //
 * //                   O\  =  /O                   //
 * //                ____/`---'\____                //
 * //              .'  \\|     |//  `.              //
 * //             /  \\|||  :  |||//  \             //
 * //            /  _||||| -:- |||||-  \            //
 * //            |   | \\\  -  /// |   |            //
 * //            | \_|  ''\---/''  |   |            //
 * //            \  .-\__  `-`  ___/-. /            //
 * //          ___`. .'  /--.--\  `. . __           //
 * //       ."" '<  `.___\_<|>_/___.'  >'"".        //
 * //      | | :  `- \`.;`\ _ /`;.`/ - ` : | |      //
 * //      \  \ `-.   \_ __\ /__ _/   .-` /  /      //
 * // ======`-.____`-.___\_____/___.-`____.-'====== //
 * //                    `=---='                    //
 * // ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^ //
 * //          佛祖保佑        永无BUG               //
 * // 佛曰:                                         //
 * //        写字楼里写字间,写字间里程序员.            //
 * //        程序人员写程序,又拿程序换酒钱.            //
 * //        酒醒只在网上坐,酒醉还来网下眠.            //
 * //        酒醉酒醒日复日,网上网下年复年.            //
 * //        但愿老死电脑间,不愿鞠躬老板前.            //
 * //        奔驰宝马贵者趣,公交自行程序员.            //
 * //        别人笑我忒疯癫,我笑自己命太贱.            //
 * //        不见满街漂亮妹,哪个归得程序员.            //
 * ///////////////////////////////////////////////////
 */
@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {

    /**
     * 这里caching不能添加put 因为添加了总会执行该方法
     * @param name
     * @return
     */
    @Cacheable(value = "user", key = "'user_name_' + #name", unless = "#result == null")
    @Override
    public User findUserByLoginName(String name) {
        return this.baseMapper.findByLoginName(name);
    }

    @Cacheable(value = "user", key = "'user_id_' + T(String).valueOf(#id)", unless = "#result == null")
    @Override
    public User findUserById(Long id) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("id", id);
        return this.baseMapper.selectUserByMap(map);
    }

    @Caching(put = {
            @CachePut(value = "user", key = "'user_id_'+T(String).valueOf(#result.id)", condition = "#result.id != null and #result.id != 0"),
            @CachePut(value = "user", key = "'user_name_'+#user.loginName", condition = "#user.loginName !=null and #user.loginName != ''")//,
//            @CachePut(value = "user", key = "'user_email_'+#user.email", condition = "#user.email != null and #user.email != ''"),
//            @CachePut(value = "user", key = "'user_tel_'+#user.tel", condition = "#user.tel != null and #user.tel != ''")
    })
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    @Override
    public User saveUser(User user) {
//        ToolUtil.entryptPassword(user);
        //密码加密
        String password = new SimpleHash("MD5", user.getPassword(), ByteSource.Util.bytes(user.getLoginName()), 1024).toString();
        user.setPassword(password);
        user.setLocked(false);
        this.baseMapper.insert(user);
        //保存用户角色关系
        this.saveUserRoles(user.getId(), user.getRoleSet());
        return this.findUserById(user.getId());
    }

    @Caching(evict = {
            @CacheEvict(value = "user", key = "'user_id_'+T(String).valueOf(#user.id)", condition = "#user.id != null and #user.id != 0"),
            @CacheEvict(value = "user", key = "'user_name_'+#user.loginName", condition = "#user.loginName !=null and #user.loginName != ''")//,
//            @CacheEvict(value = "user", key = "'user_email_'+#user.email", condition = "#user.email != null and #user.email != ''"),
//            @CacheEvict(value = "user", key = "'user_tel_'+#user.tel", condition = "#user.tel != null and #user.tel != ''"),
    })
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    @Override
    public User updateUser(User user) {
        this.baseMapper.updateById(user);
        //先解除用户跟角色的关系
        this.dropUserRolesByUserId(user.getId());
        this.saveUserRoles(user.getId(), user.getRoleSet());
        return user;
    }

    @Transactional(readOnly = false, rollbackFor = Exception.class)
    @Override
    public void saveUserRoles(Long id, Set<Role> roleSet) {
        this.baseMapper.saveUserRoles(id, roleSet);
    }

    @Transactional(readOnly = false, rollbackFor = Exception.class)
    @Override
    public void dropUserRolesByUserId(Long id) {
        this.baseMapper.dropUserRolesByUserId(id);
    }

    @Caching(evict = {
            @CacheEvict(value = "user", key = "'user_id_'+T(String).valueOf(#user.id)", condition = "#user.id != null and #user.id != 0"),
            @CacheEvict(value = "user", key = "'user_name_'+#user.loginName", condition = "#user.loginName !=null and #user.loginName != ''")//,
//            @CacheEvict(value = "user", key = "'user_email_'+#user.email", condition = "#user.email != null and #user.email != ''"),
//            @CacheEvict(value = "user", key = "'user_tel_'+#user.tel", condition = "#user.tel != null and #user.tel != ''")
    })
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    @Override
    public void deleteUser(User user) {
        user.setStatus(2);
        user.updateById();
    }

    @Override
    public int userCount(String param) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("login_name", param);
        int count = this.baseMapper.selectCount(wrapper);
        return count;
    }

    @Override
    public Map selectUserModuleCount() {
        return this.baseMapper.selectUserModuleCount();
    }

}