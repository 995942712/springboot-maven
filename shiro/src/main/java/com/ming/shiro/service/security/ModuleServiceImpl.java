package com.ming.shiro.service.security;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.ming.shiro.dao.security.ModuleDao;
import com.ming.shiro.domain.security.Module;
import com.ming.shiro.domain.vo.ModuleVo;
import com.ming.shiro.domain.vo.ZtreeVo;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;

/**
 * 模块管理 Service 实现类
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
public class ModuleServiceImpl extends ServiceImpl<ModuleDao, Module> implements ModuleService {

    @Cacheable(value = "allModules", key = "'allModules_isShow_' + #map['isShow'].toString()", unless = "#result == null or #result.size() == 0")
    @Override
    public List<Module> selectAllModules(Map<String, Object> map) {
        return this.baseMapper.getModules(map);
    }

    @Override
    public List<ZtreeVo> showTreeModules() {
        QueryWrapper<Module> wrapper = new QueryWrapper<>();
        wrapper.eq("status", 0);
        wrapper.eq("is_show", true);
        wrapper.orderBy(false, false, "sort");
        List<Module> modules = baseMapper.selectList(wrapper);
        List<ZtreeVo> ztreeVos = Lists.newArrayList();
        return getZTree(null, modules, ztreeVos);
    }

    @Cacheable(value = "allModules", key = "'user_module_'+T(String).valueOf(#id)", unless = "#result == null or #result.size() == 0")
    @Override
    public List<ModuleVo> getModuleVoByUser(Long id) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("userId", id);
        map.put("parentId", null);
        return this.baseMapper.selectModuleVoByUser(map);
    }

    @Caching(evict = {@CacheEvict(value = "allModules", allEntries = true), @CacheEvict(value = "user", allEntries = true)})
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    @Override
    public void saveOrUpdateModule(Module module) {
        this.saveOrUpdate(module);
    }

    @Override
    public int getCountByCode(String code) {
        QueryWrapper<Module> wrapper = new QueryWrapper<>();
        wrapper.eq("status", 0);
        wrapper.eq("code", code);
        return baseMapper.selectCount(wrapper);
    }

    @Override
    public int getCountByName(String name) {
        QueryWrapper<Module> wrapper = new QueryWrapper<>();
        wrapper.eq("status", 0);
        wrapper.eq("name", name);
        return baseMapper.selectCount(wrapper);
    }

    /**
     * 递归拉取菜单树的数据
     */
    private List<ZtreeVo> getZTree(ZtreeVo tree, List<Module> modules, List<ZtreeVo> ztreeVos) {
        Long parentId = tree == null ? null : tree.getId();
        List<ZtreeVo> childList = Lists.newArrayList();
        for (Module m : modules) {
            if (parentId == m.getParentId()) {
                ZtreeVo ztreeVO = new ZtreeVo();
                ztreeVO.setId(m.getId());
                ztreeVO.setName(m.getName());
                ztreeVO.setParentId(parentId);
                childList.add(ztreeVO);
                getZTree(ztreeVO, modules, ztreeVos);
            }
        }
        if (tree != null) {
            tree.setChildren(childList);
        } else {
            ztreeVos = childList;
        }
        return ztreeVos;
    }

}