package com.ming.shiro.dao.security;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ming.shiro.domain.security.Module;
import com.ming.shiro.domain.vo.ModuleVo;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import java.util.Map;

/**
 * 模块管理Dao
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
@Mapper
public interface ModuleDao extends BaseMapper<Module> {

    /**
     * 得到菜单
     * @param map
     * @return
     */
    List<Module> getModules(Map map);
    /**
     * 显示所有菜单列表
     * @param map
     * @return
     */
    List<Module> showAllModulesList(Map map);

    /**
     * 选择“按用户显示菜单”
     * @param map
     * @return
     */
    List<ModuleVo> selectModuleVoByUser(Map<String, Object> map);

}