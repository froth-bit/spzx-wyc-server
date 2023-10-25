package com.itperson.spzx.manager.controller;

import com.itperson.spzx.manager.service.SysMenuService;
import com.itperson.spzx.manager.service.SysUserService;
import com.itperson.spzx.manager.service.ValidateCodeService;
import com.itperson.spzx.model.dto.system.LoginDto;
import com.itperson.spzx.model.vo.common.Result;
import com.itperson.spzx.model.vo.common.ResultCodeEnum;
import com.itperson.spzx.model.vo.system.LoginVo;
import com.itperson.spzx.model.vo.system.SysMenuVo;
import com.itperson.spzx.model.vo.system.ValidateCodeVo;
import com.itperson.spzx.utils.AuthContextUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name="用户接口")
@RestController
@RequestMapping("/admin/system/index")
public class IndexController {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private ValidateCodeService validateCodeService;
    @Autowired
    private SysMenuService sysMenuService;

    @Operation(summary = "登录的方法")
    @PostMapping("/login")
    public Result login (@RequestBody LoginDto loginDto){
        LoginVo loginVo = sysUserService.login(loginDto);
        return Result.build(loginVo, ResultCodeEnum.SUCCESS);
    }

    @GetMapping("/generateValidateCode")
    public Result<ValidateCodeVo> generateValidateCode(){
        ValidateCodeVo validateCodeVo = validateCodeService.generateValidateCode();
        return Result.build(validateCodeVo, ResultCodeEnum.SUCCESS);
    }

    @GetMapping("/getUserInfo")
    public Result getUserInfo(){
        return Result.build(AuthContextUtil.getAuthContext(),ResultCodeEnum.SUCCESS);
    }

    @GetMapping("/logout")
    public Result logout(@RequestHeader(name = "token") String token){
        sysUserService.logout(token);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }

    //查询用户可以操作的菜单
    @GetMapping("/getUserMenu")
    public Result getUserMenu(){
        List<SysMenuVo> list = sysMenuService.findMenusByUserId();
        return Result.build(list,ResultCodeEnum.SUCCESS);
    }

}
