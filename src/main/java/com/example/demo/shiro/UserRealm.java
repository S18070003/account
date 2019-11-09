package com.example.demo.shiro;

import com.example.demo.service.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;


public class UserRealm extends AuthorizingRealm {
    @Autowired
    public User userService;
    /*
     * 执行授权逻辑
     * @param principalCollection
     * @return
     * */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行授权逻辑");
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
   /*     Integer roleType=sysAdministratorService.getRoleType();
        if(roleType==1){
            info.addRole("superadmin");
        }else if(roleType==0){
            info.addRole("businessadmin");
        }
        //添加资源的授权字符
*//*        SysUser sysUser =(SysUser) principalCollection.getPrimaryPrincipal();//获取身份信息
        List<SysRole> sysRoles=sysUser.getSysRoleList();
        if(!sysRoles.isEmpty()){
            for(SysRole sysRole : sysRoles){
                info.addRole(sysRole.getName());
                List<SysAcl> sysAcls =sysAclService.selectAclListByRoleId(sysRole.getId());
                for(SysAcl sysAcl:sysAcls){
                    info.addStringPermission(sysAcl.getPermission());
                }
                //for()
            }
        }*/
        return info;
    }

        /*
     * 执行认证逻辑
     * @param token
     * @return
     * @throws AuthenticationException
    */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

            System.out.println("执行认证逻辑");
            //获得数据库账号与密码
            Subject subject = SecurityUtils.getSubject();
            String username=(String) token.getPrincipal();
        System.out.println(username);
            Session session = subject.getSession();
            com.example.demo.entity.User sysUser=userService.selectUserByUsername(username);
            if(sysUser==null){
                return null;
            }
            return new SimpleAuthenticationInfo(sysUser,sysUser.getPassword(),getName());
//            return new SimpleAuthenticationInfo("abc","abc",getName());
    }
    @Override
    public String getName(){
        return "userRealm";
    }
}
