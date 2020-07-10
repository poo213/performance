package com.njmetro.performance.controller;


import com.njmetro.performance.domain.BonusPoint;
import com.njmetro.performance.domain.CheckBoxInfo;
import com.njmetro.performance.domain.TabInfo;
import com.njmetro.performance.service.BonusPointService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 加分项 前端控制器
 * </p>
 *
 * @author zc
 * @since 2020-07-03
 */
@RestController
@RequestMapping("/bonus-point")
@RequiredArgsConstructor
public class BonusPointController {

    private final BonusPointService bonusPointService;

    @GetMapping("/getBonusPoint")
    public List<BonusPoint> getBonusPoint() {

        return bonusPointService.list();
    }

    @GetMapping("/getTab")
    public List<TabInfo> getTab() {
        List<BonusPoint> bonusPointList = bonusPointService.list();

        List<CheckBoxInfo> checkBoxInfoListCommen =new ArrayList<>();
        List<CheckBoxInfo> checkBoxInfoListOffice =new ArrayList<>();
        List<CheckBoxInfo> checkBoxInfoListCompute =new ArrayList<>();
        List<CheckBoxInfo> checkBoxInfoListIt =new ArrayList<>();
        List<CheckBoxInfo> checkBoxInfoListEletrc =new ArrayList<>();
        List<CheckBoxInfo> checkBoxInfoListHead =new ArrayList<>();
        for (BonusPoint item : bonusPointList
        ) {
            if (item.getTeam().equals("共性标准")) {
                CheckBoxInfo checkBoxInfo = new CheckBoxInfo();
                checkBoxInfo.setId(item.getId());
                checkBoxInfo.setTitle(item.getReason());
                checkBoxInfoListCommen.add(checkBoxInfo);
            }
            if (item.getTeam().equals("综合科")) {
                CheckBoxInfo checkBoxInfo = new CheckBoxInfo();
                checkBoxInfo.setId(item.getId());
                checkBoxInfo.setTitle(item.getReason());
                checkBoxInfoListOffice.add(checkBoxInfo);
            }
            if (item.getTeam().equals("计量科")) {
                CheckBoxInfo checkBoxInfo = new CheckBoxInfo();
                checkBoxInfo.setId(item.getId());
                checkBoxInfo.setTitle(item.getReason());
                checkBoxInfoListCompute.add(checkBoxInfo);
            }
            if (item.getTeam().equals("信息科")) {
                CheckBoxInfo checkBoxInfo = new CheckBoxInfo();
                checkBoxInfo.setId(item.getId());
                checkBoxInfo.setTitle(item.getReason());
                checkBoxInfoListIt.add(checkBoxInfo);
            }
            if (item.getTeam().equals("电子科")) {
                CheckBoxInfo checkBoxInfo = new CheckBoxInfo();
                checkBoxInfo.setId(item.getId());
                checkBoxInfo.setTitle(item.getReason());
                checkBoxInfoListEletrc.add(checkBoxInfo);
            }
            if (item.getTeam().equals("科室负责人")) {
                CheckBoxInfo checkBoxInfo = new CheckBoxInfo();
                checkBoxInfo.setId(item.getId());
                checkBoxInfo.setTitle(item.getReason());
                checkBoxInfoListHead.add(checkBoxInfo);
            }
        }
        TabInfo tabInfoCommen = new TabInfo();
        tabInfoCommen.setKey("0");
        tabInfoCommen.setLabel("共性标准");
        tabInfoCommen.setList(checkBoxInfoListCommen);

        TabInfo tabInfoOffice = new TabInfo();
        tabInfoOffice.setKey("1");
        tabInfoOffice.setLabel("综合科");
        tabInfoOffice.setList(checkBoxInfoListOffice);

        TabInfo tabInfoCompute = new TabInfo();
        tabInfoCompute.setKey("2");
        tabInfoCompute.setLabel("计量科");
        tabInfoCompute.setList(checkBoxInfoListCompute);

        TabInfo tabInfoIt = new TabInfo();
        tabInfoIt.setKey("3");
        tabInfoIt.setLabel("信息科");
        tabInfoIt.setList(checkBoxInfoListIt);

        TabInfo tabInfoEletrc = new TabInfo();
        tabInfoEletrc.setKey("4");
        tabInfoEletrc.setLabel("电子科");
        tabInfoEletrc.setList(checkBoxInfoListEletrc);

        TabInfo tabInfoHead = new TabInfo();
        tabInfoHead.setKey("5");
        tabInfoHead.setLabel("科室负责人");
        tabInfoHead.setList(checkBoxInfoListHead);


        List<TabInfo> tabInfoList = new ArrayList<>();
        tabInfoList.add(tabInfoCommen);
        tabInfoList.add(tabInfoOffice);
        tabInfoList.add(tabInfoCompute);
        tabInfoList.add(tabInfoIt);
        tabInfoList.add(tabInfoEletrc);
        tabInfoList.add(tabInfoHead);


        return tabInfoList;
    }
}

