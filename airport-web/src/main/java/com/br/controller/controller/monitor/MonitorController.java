package com.br.controller.controller.monitor;

import com.br.entity.utils.BreadCrumb;
import com.br.entity.utils.Result;
import com.br.service.constant.RequestRouteConstant;
import com.br.service.constant.ViewConstant;
import com.br.service.enumeration.CommonEnumeration;
import com.br.service.service.task.TrafficTaskService;
import com.br.service.service.traffic.AewService;
import com.br.service.service.traffic.PositionService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * 地图服务控制器
 *
 * @Author Zero
 * @Date 2019 02 22
 */
@Controller
public class MonitorController {

    // 位置服务
    @Autowired
    private PositionService positionService;

    // 任务服务
    @Autowired
    private TrafficTaskService trafficTaskService;

    // 预警信息服务
    @Autowired
    private AewService aewService;

    /**
     * 预警监管视图
     *
     * @param model 响应结果集
     * @return 视图
     */
    @RequiresPermissions(RequestRouteConstant.REQUEST_ROUTE_MONITOR + RequestRouteConstant.REQUEST_ROUTE_MONITOR_AEW)
    @RequestMapping(value = RequestRouteConstant.REQUEST_ROUTE_MONITOR + RequestRouteConstant.REQUEST_ROUTE_MONITOR_AEW, method = RequestMethod.GET)
    public String aewPage(Model model) {
        model.addAttribute("breadcrumb", new BreadCrumb[]{new BreadCrumb("首页", RequestRouteConstant.REQUEST_ROUTE_HOME), new BreadCrumb("预警监管", RequestRouteConstant.REQUEST_ROUTE_MONITOR + RequestRouteConstant.REQUEST_ROUTE_MONITOR_AEW)});
        return ViewConstant.VIEW_DIR_MONITOR + ViewConstant.VIEW_FILE_MONITOR_AEW;
    }

    /**
     * /**
     * 预警监管数据集合
     *
     * @param currentPage 当前页
     * @param pageSize    页面数量
     * @return List<AewInfo>
     */
    @RequestMapping(value = RequestRouteConstant.REQUEST_ROUTE_MONITOR + RequestRouteConstant.REQUEST_ROUTE_MONITOR_AEW + RequestRouteConstant.REQUEST_ROUTE_MONITOR_AEW_List, method = RequestMethod.GET)
    @ResponseBody
    public Result aewList(@RequestParam("currentPage") Integer currentPage, @RequestParam("pageSize") Integer pageSize) {
        PageHelper.startPage(currentPage, pageSize, true);
        PageInfo pageInfo = new PageInfo(this.aewService.findAll());
        Result result = new Result();
        result.setStatus(CommonEnumeration.SUCCESS.getStatus());
        result.setCode(CommonEnumeration.SUCCESS.getCode());
        result.getData().put("pageInfo", pageInfo);
        return result;
    }

    /**
     * 交通监管
     *
     * @param model 响应结果集
     * @return 视图
     */
    @RequiresPermissions({RequestRouteConstant.REQUEST_ROUTE_MONITOR + RequestRouteConstant.REQUEST_ROUTE_MONITOR_TRAFFIC})
    @RequestMapping(value = RequestRouteConstant.REQUEST_ROUTE_MONITOR + RequestRouteConstant.REQUEST_ROUTE_MONITOR_TRAFFIC, method = RequestMethod.GET)
    public String trafficPage(Model model) {
        model.addAttribute("breadcrumb", new BreadCrumb[]{new BreadCrumb("首页", RequestRouteConstant.REQUEST_ROUTE_HOME), new BreadCrumb("交通监管", RequestRouteConstant.REQUEST_ROUTE_MONITOR + RequestRouteConstant.REQUEST_ROUTE_MONITOR_TRAFFIC)});
        return ViewConstant.VIEW_DIR_MONITOR + ViewConstant.VIEW_FILE_MONITOR_TRAFFIC;
    }


    /**
     * 交通工具信息回传
     *
     * @param trafficInfo 交通工具信息
     */
    @RequestMapping(value = RequestRouteConstant.REQUEST_ROUTE_MONITOR + RequestRouteConstant.REQUEST_ROUTE_MONITOR_TRAFFIC + RequestRouteConstant.REQUEST_ROUTE_MONITOR_TRAFFIC_INFO, method = RequestMethod.POST)
    public void trafficInfo(@RequestBody String trafficInfo) {
        System.out.println(trafficInfo);
        return;
    }

}