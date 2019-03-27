package com.ming.shiro.controller.security;

import com.google.common.collect.Maps;
import com.ming.shiro.controller.BaseController;
import com.ming.shiro.domain.Result;
import com.ming.shiro.domain.security.Module;
import com.ming.shiro.domain.vo.ZtreeVo;
import com.ming.shiro.service.security.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 模块管理 Controller
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
@Controller
@RequestMapping("/module")
public class ModuleController extends BaseController {

    @GetMapping(value = "/list")
    public String list() {
        return "security/moduleTree";
    }

    @ResponseBody
    @PostMapping(value = "/tree")
    public Result tree() {
        List<ZtreeVo> ztreeVos = this.moduleService.showTreeModules();
        return Result.create(ztreeVos);
    }

    @ResponseBody
    @PostMapping(value = "/treelist")
    public Result treelist() {
        Map<String, Object> map = Maps.newHashMap();
        map.put("parentId",null);
        map.put("isShow",false);
        List<Module> list = this.moduleService.selectAllModules(map);
        return Result.create(list);
    }

}