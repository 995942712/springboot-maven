package com.ming.shiro.dao.security;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ming.shiro.domain.security.UserInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户信息 Dao
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
public interface UserInfoDao extends BaseMapper<UserInfo> {

}