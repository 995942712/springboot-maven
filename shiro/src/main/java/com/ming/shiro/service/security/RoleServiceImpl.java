package com.ming.shiro.service.security;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ming.shiro.dao.security.RoleDao;
import com.ming.shiro.domain.security.Module;
import com.ming.shiro.domain.security.Role;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Set;

/**
 * 角色管理 Service 实现类
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
public class RoleServiceImpl extends ServiceImpl<RoleDao, Role> implements RoleService {

    @Caching(put = {@CachePut(value = "role", key = "'role_id_'+T(String).valueOf(#result.id)", condition = "#result.id != null and #result.id != 0")},
            evict = {@CacheEvict(value = "roleAll", key = "'roleAll'")})
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    @Override
    public Role saveRole(Role role) {
        this.baseMapper.insert(role);
        this.baseMapper.saveRoleModules(role.getId(), role.getModuleSet());
        return role;
    }

    @Cacheable(value = "roleAll", key = "'roleAll'", unless = "#result == null or #result.size() == 0")
    @Override
    public List<Role> selectAll() {
        QueryWrapper<Role> wrapper = new QueryWrapper<>();
        wrapper.eq("status", 0);
        List<Role> roleList = this.baseMapper.selectList(wrapper);
        return roleList;
    }

    @Cacheable(value = "role", key = "'role_id_'+T(String).valueOf(#id)", unless = "#result == null")
    @Override
    public Role getRoleById(Long id) {
        return this.baseMapper.selectRoleById(id);
    }

    @Caching(evict = {
            @CacheEvict(value = "role", key = "'role_id_' + T(String).valueOf(#role.id)"),
            @CacheEvict(value = "roleAll", key = "'roleAll'"),
            @CacheEvict(value = "user", allEntries = true),
            @CacheEvict(value = "allMenus", allEntries = true)
    })
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    @Override
    public void updateRole(Role role) {
        this.baseMapper.updateById(role);
        this.baseMapper.dropRoleModules(role.getId());
        this.baseMapper.saveRoleModules(role.getId(), role.getModuleSet());
    }

    @Caching(evict = {
            @CacheEvict(value = "role", key = "'role_id_' + T(String).valueOf(#role.id)"),
            @CacheEvict(value = "roleAll", key = "'roleAll'"),
            @CacheEvict(value = "user", allEntries = true)
    })
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    @Override
    public void deleteRole(Role role) {
        role.setStatus(2);
        this.baseMapper.updateById(role);
        this.baseMapper.dropRoleModules(role.getId());
        this.baseMapper.dropRoleUsers(role.getId());
    }

    @Transactional(readOnly = false, rollbackFor = Exception.class)
    @Override
    public void saveRoleModules(Long id, Set<Module> moduleSet) {
        this.baseMapper.saveRoleModules(id, moduleSet);
    }

    @Transactional(readOnly = false, rollbackFor = Exception.class)
    @Override
    public void dropRoleMenus(Long id) {
        this.baseMapper.dropRoleModules(id);
    }

    @Override
    public Integer getRoleNameCount(String name) {
        QueryWrapper<Role> wrapper = new QueryWrapper<>();
        wrapper.eq("name", name);
        return this.baseMapper.selectCount(wrapper);
    }

}