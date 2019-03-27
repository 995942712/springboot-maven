//package com.ming.shiro.filter;
//
//import lombok.Setter;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.shiro.cache.Cache;
//import org.apache.shiro.cache.CacheManager;
//import org.apache.shiro.session.Session;
//import org.apache.shiro.session.mgt.DefaultSessionKey;
//import org.apache.shiro.session.mgt.SessionManager;
//import org.apache.shiro.subject.Subject;
//import org.apache.shiro.web.filter.AccessControlFilter;
//import org.apache.shiro.web.util.WebUtils;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import java.io.Serializable;
//import java.util.ArrayDeque;
//import java.util.Deque;
//
///**
// * 自定义过滤器,进行用户访问控制
// *
// * ///////////////////////////////////////////////////
// * //                    _ooOoo_                    //
// * //                   o8888888o                   //
// * //                   88" . "88                   //
// * //                   (| -_- |)                   //
// * //                   O\  =  /O                   //
// * //                ____/`---'\____                //
// * //              .'  \\|     |//  `.              //
// * //             /  \\|||  :  |||//  \             //
// * //            /  _||||| -:- |||||-  \            //
// * //            |   | \\\  -  /// |   |            //
// * //            | \_|  ''\---/''  |   |            //
// * //            \  .-\__  `-`  ___/-. /            //
// * //          ___`. .'  /--.--\  `. . __           //
// * //       ."" '<  `.___\_<|>_/___.'  >'"".        //
// * //      | | :  `- \`.;`\ _ /`;.`/ - ` : | |      //
// * //      \  \ `-.   \_ __\ /__ _/   .-` /  /      //
// * // ======`-.____`-.___\_____/___.-`____.-'====== //
// * //                    `=---='                    //
// * // ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^ //
// * //          佛祖保佑        永无BUG               //
// * // 佛曰:                                         //
// * //        写字楼里写字间,写字间里程序员.            //
// * //        程序人员写程序,又拿程序换酒钱.            //
// * //        酒醒只在网上坐,酒醉还来网下眠.            //
// * //        酒醉酒醒日复日,网上网下年复年.            //
// * //        但愿老死电脑间,不愿鞠躬老板前.            //
// * //        奔驰宝马贵者趣,公交自行程序员.            //
// * //        别人笑我忒疯癫,我笑自己命太贱.            //
// * //        不见满街漂亮妹,哪个归得程序员.            //
// * ///////////////////////////////////////////////////
// */
//@Setter
//@Slf4j
//public class KickoutSessionFilter extends AccessControlFilter {
//
//    //踢出后到的地址
//    private String kickoutUrl;
//    //踢出之前登录的/之后登录的用户,默认false踢出之前登录的用户
//    private boolean kickoutAfter = false;
//    //同一个帐号最大会话数,默认1
//    private int maxSession = 1;
//    private SessionManager sessionManager;
//    private Cache<String, Deque<Serializable>> cache;
//
//    /**
//     * 设置Cache的key的前缀,必须和ehcache缓存配置中的缓存name一致
//     * @param cacheManager
//     */
//    public void setCacheManager(CacheManager cacheManager) {
//        this.cache = cacheManager.getCache("shiro");
//    }
//
//    /**
//     * 表示是否允许访问,mappedValue就是[urls]配置中拦截器参数部分,如果允许访问返回true,否则false
//     * @param request
//     * @param response
//     * @param mappedValue
//     * @return
//     * @throws Exception
//     */
//    @Override
//    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
//        return false;
//    }
//
//    /**
//     * 表示当访问拒绝时是否已经处理了,如果返回true表示需要继续处理,如果返回false表示该拦截器实例已经处理了,将直接返回即可
//     * @param request
//     * @param response
//     * @return
//     * @throws Exception
//     */
//    @Override
//    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
////        Subject subject = getSubject(request, response);
////        //没有登录授权,且没有记住我
////        if (!subject.isAuthenticated() && !subject.isRemembered()) {
////            return true;
////        }
////        Session session = subject.getSession();
////        log.info("==========session时间设置:" + String.valueOf(session.getTimeout()) + "==========");
////        try {
////            //当前用户
////            String username = (String) subject.getPrincipal();
////            Serializable sessionId = session.getId();
////            //读取缓存用户,没有就存入
////            Deque<Serializable> deque = cache.get(username);
////            System.out.println("===当前deque：==" + deque);
////            if (deque == null) {
////                //初始化队列
////                deque = new ArrayDeque<>();
////            }
////            //如果队列里没有此sessionId,且用户没有被踢出,放入队列
////            if (!deque.contains(sessionId) && session.getAttribute("kickout") == null) {
////                //将sessionId存入队列
////                deque.push(sessionId);
////                //将用户的sessionId队列缓存
////                cache.put(username, deque);
////            }
////            //如果队列里的sessionId数超出最大会话数,开始踢人
////            while (deque.size() > maxSession) {
////                log.info("==========deque队列长度:" + deque.size() + "==========");
////                Serializable kickoutSessionId = null;
////                //是否踢出后来登录的,默认是false,即后者登录的用户踢出前者登录的用户
////                if (kickoutAfter) {
////                    kickoutSessionId = deque.removeFirst();
////                } else {
////                    kickoutSessionId = deque.removeLast();
////                }
////                //踢出后再更新下缓存队列
////                cache.put(username, deque);
////                try {
////                    //获取被踢出的sessionId的session对象
////                    Session kickoutSession = sessionManager.getSession(new DefaultSessionKey(kickoutSessionId));
////                    if (kickoutSession != null) {
////                        //设置会话的kickout属性表示踢出了
////                        kickoutSession.setAttribute("kickout", true);
////                    }
////                } catch (Exception e) {
////
////                }
////            }
////            //如果被踢出了(前者或后者)直接退出,重定向到踢出后的地址
////            if ((Boolean) session.getAttribute("kickout") != null && (Boolean) session.getAttribute("kickout") == true) {
////                //会话被踢出了
////                try {
////                    //退出登录
////                    subject.logout();
////                } catch (Exception e) {
////
////                }
////                saveRequest(request);
////                System.out.println("==踢出后用户重定向的路径kickoutUrl:" + kickoutUrl);
////                WebUtils.issueRedirect(request, response, kickoutUrl);
////                return false;
////            }
////            return true;
////        } catch (Exception e) {
////            System.out.println("控制用户在线数量【lyd-admin-->KickoutSessionFilter.onAccessDenied】异常！" + e);
////            return false;
////        }
//        return true;
//    }
//
//}