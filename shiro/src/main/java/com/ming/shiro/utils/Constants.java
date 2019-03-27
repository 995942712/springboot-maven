package com.ming.shiro.utils;

/**
 * 常量参数
 *
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
public class Constants {

    /**
     * shiro采用加密算法
     */
    public static final String HASH_ALGORITHM = "SHA-1";
    /**
     * 生成Hash值的迭代次数
     */
    public static final int HASH_INTERATIONS = 1024;
    /**
     * 生成盐的长度
     */
    public static final int SALT_SIZE = 8;
    /**
     * 验证码
     */
    public static final String VALIDATE_CODE = "validateCode";
    /**
     * 系统用户默认密码
     */
    public static final String DEFAULT_PASSWORD = "123456";
    /**
     * 定时任务状态:正常
     */
    public static final Integer QUARTZ_STATUS_NOMAL = 0;
    /**
     * 定时任务状态:暂停
     */
    public static final Integer QUARTZ_STATUS_PUSH = 1;
    /**
     * 评论类型：1文章评论
     */
    public static final Integer COMMENT_TYPE_ARTICLE_COMMENT = 1;
    /**
     * 评论类型：2.系统留言
     */
    public static final Integer COMMENT_TYPE_LEVING_A_MESSAGE = 2;

}