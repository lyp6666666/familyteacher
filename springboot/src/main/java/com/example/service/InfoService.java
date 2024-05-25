package com.example.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.example.common.enums.StatusEnum;
import com.example.entity.Account;
import com.example.entity.Info;
import com.example.mapper.InfoMapper;
import com.example.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.jdbc.Null;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 教员资料表业务处理
 **/
@Service
public class InfoService {

    @Resource
    private InfoMapper infoMapper;

    /**
     * 新增
     */
    public void add(Info info) {

        Info dbInfo = infoMapper.selectByTeacherId(info.getTeacherId());
        //先查询表里有没有该教员要提交的资料信息
        if (ObjectUtil.isEmpty(dbInfo)){
            infoMapper.insert(info);
        }else{
            //清除管理员审核时的几个字段
            info.setLevel("");
            info.setStar("");
            info.setReason("");
            infoMapper.updateById(info);
        }

    }

    /**
     * 删除
     */
    public void deleteById(Integer id) {
        infoMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            infoMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    public void updateById(Info info) {
        if (ObjectUtil.isNotEmpty(info.getStatus()) && StatusEnum.CHECK_OK.status.equals(info.getStatus())){
            //给教员一个教员编号
            if (ObjectUtil.isEmpty(info.getCode())){
                info.setCode(DateUtil.format(new Date(),"yyyyMMddHHmmss"));
            }
        }
        infoMapper.updateById(info);
    }

    /**
     * 根据ID查询
     */
    public Info selectById(Integer id) {
        return infoMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    public List<Info> selectAll(Info info) {
        return infoMapper.selectAll(info);
    }

    /**
     * 分页查询
     */
    public PageInfo<Info> selectPage(Info info, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Info> list = infoMapper.selectAll(info);
        return PageInfo.of(list);
    }

    public Info selectByTeacherId(Integer id) {
        return infoMapper.selectByTeacherId(id);
    }
}