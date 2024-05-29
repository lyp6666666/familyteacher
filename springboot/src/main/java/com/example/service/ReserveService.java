package com.example.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.example.common.enums.ResultCodeEnum;
import com.example.common.enums.RoleEnum;
import com.example.common.enums.StatusEnum;
import com.example.entity.Account;
import com.example.entity.Reserve;
import com.example.exception.CustomException;
import com.example.mapper.ReserveMapper;
import com.example.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 公告信息表业务处理
 **/
@Service
public class ReserveService {

    @Resource
    private ReserveMapper reserveMapper;

    /**
     * 新增
     */
    public void add(Reserve reserve) {
        //判断家长是否提交过申请
        Reserve dbReserve = reserveMapper.selectByUserIdAndTeacherId(reserve.getUserId(),reserve.getTeacherId());
        if (ObjectUtil.isEmpty(dbReserve)){
            reserveMapper.insert(reserve);
            return;
        }
        //如果已拒绝，则再次提交
        if (StatusEnum.RESERVE_NO.status.equals(dbReserve.getStatus())){
            reserve.setId(dbReserve.getId());
            reserveMapper.updateById(reserve);
        }
        //如果已同意或待确认，则不允许再次提交申请
        if (StatusEnum.RESERVING.status.equals(dbReserve.getStatus()) || StatusEnum.RESERVE_OK.status.equals(dbReserve.getStatus())){
            throw new CustomException(ResultCodeEnum.RESERVE_AGAIN_ERROR);
        }
    }

    /**
     * 删除
     */
    public void deleteById(Integer id) {
        reserveMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            reserveMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    public void updateById(Reserve reserve) {
        reserveMapper.updateById(reserve);
    }

    /**
     * 根据ID查询
     */
    public Reserve selectById(Integer id) {
        return reserveMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    public List<Reserve> selectAll(Reserve reserve) {
        return reserveMapper.selectAll(reserve);
    }

    /**
     * 分页查询
     */
    public PageInfo<Reserve> selectPage(Reserve reserve, Integer pageNum, Integer pageSize) {
        Account currentUser = TokenUtils.getCurrentUser();
        if (RoleEnum.USER.name().equals(currentUser.getRole())){
            reserve.setUserId(currentUser.getId());
        }
        if (RoleEnum.TEACHER.name().equals(currentUser.getRole())){
            reserve.setTeacherId(currentUser.getId());
        }

        PageHelper.startPage(pageNum, pageSize);
        List<Reserve> list = reserveMapper.selectAll(reserve);
        return PageInfo.of(list);
    }

}