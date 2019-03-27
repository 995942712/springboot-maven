package com.ming.shiro.service.security;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ming.shiro.domain.security.Module;
import com.ming.shiro.domain.vo.ModuleVo;
import com.ming.shiro.domain.vo.ZtreeVo;
import java.util.List;
import java.util.Map;

/**
 * 模块管理 Service
 *
 * ////////////////////////////////////////////////////////
 * //                     _ooOoo_                        //
 * //                    o8888888o                       //
 * //                    88" . "88                       //
 * //                    (| ^_^ |)                       //
 * //                    O\  =  /O                       //
 * //                 ____/`---'\____                    //
 * //               .'  \\|     |//  `.                  //
 * //              /  \\|||  :  |||//  \                 //
 * //             /  _||||| -:- |||||-  \                //
 * //             |   | \\\  -  /// |   |                //
 * //             | \_|  ''\---/''  |   |                //
 * //             \  .-\__  `-`  ___/-. /                //
 * //           ___`. .'  /--.--\  `. . ___              //
 * //         ."" '<  `.___\_<|>_/___.'  >'"".           //
 * //       | | :  `- \`.;`\ _ /`;.`/ - ` : | |          //
 * //       \  \ `-.   \_ __\ /__ _/   .-` /  /          //
 * // ========`-.____`-.___\_____/___.-`____.-'========= //
 * //                      `=---='                       //
 * // ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^ //
 * //       佛祖保佑       永不宕机     永无BUG            //
 * ////////////////////////////////////////////////////////
 */
public interface ModuleService extends IService<Module> {

    /**
     * 选择所有菜单
     * @param map
     * @return
     */
    List<Module> selectAllModules(Map<String, Object> map);

    /**
     * 显示树菜单
     * @return
     */
    List<ZtreeVo> showTreeModules();

    /**
     * 获取用户显示菜单
     * @param id
     * @return
     */
    List<ModuleVo> getModuleVoByUser(Long id);

    /**
     * 保存或更新菜单
     * @param module
     */
    void saveOrUpdateModule(Module module);

    /**
     * 获得按权限计算
     * @param code
     * @return
     */
    int getCountByCode(String code);

    /**
     * 按姓名计算
     * @param name
     * @return
     */
    int getCountByName(String name);

}