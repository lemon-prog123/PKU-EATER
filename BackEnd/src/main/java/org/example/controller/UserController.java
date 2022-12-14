package org.example.controller;

import org.example.controller.viewobject.UserVO;
import org.example.error.BusinessException;
import org.example.error.EmBusinessError;
import org.example.response.CommonReturnType;
import org.example.service.UserService;
import org.example.service.model.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;



//如果不加ResponseBody的话
//就要用RestController
@Controller("user")
@RequestMapping("/user")
//@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    private HttpSession session;

    @RequestMapping(value = "/get")
    @ResponseBody
    public CommonReturnType getUser(@RequestParam(name="id")Integer id) throws BusinessException {
        //调用service服务获取对应id的用户对象并返回给前端
       UserModel userModel = userService.getUserById(id);

       //若获取的对应用户信息不存在
        if(userModel == null){
            throw new BusinessException(EmBusinessError.USER_NOT_EXIST);
        }

       //将核心领域模型用户对象转化为可供UI使用的viewobject
       UserVO userVO =  convertFromModel(userModel);

       //返回通用对象
       return CommonReturnType.create(userVO);
    }

    private UserVO convertFromModel(UserModel userModel){
        if(userModel == null){
            return null;
        }
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(userModel, userVO);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        userVO.setBirthday(sf.format(userModel.getBirthday()));
        return userVO;
    }


    //定义exceptionhandler解决未被controller层吸收的exception
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Object handlerException(HttpServletRequest request, Exception ex){
        Map<String,Object> responseData = new HashMap<>();
        if(ex instanceof BusinessException){
            BusinessException businessException = (BusinessException)ex;
            responseData.put("errCode",businessException.getErrCode());
            responseData.put("errMsg",businessException.getErrMsg());
        }else{
            responseData.put("errCode",EmBusinessError.UNKNOWN_ERROR.getErrCode());
            responseData.put("errMsg",EmBusinessError.UNKNOWN_ERROR.getErrMsg());
        }
        return CommonReturnType.create(responseData,"fail");
    }

    /***
     * 使用凯撒加密方式加密数据
     * @param orignal 原文
     * @param key 密钥
     * @return 加密后的字符
     */

    private static String encryptKaisa(String orignal, int key) {
        //将字符串转换为数组
        char[] chars = orignal.toCharArray();
        StringBuffer buffer = new StringBuffer();
        //遍历数组
        for(char aChar : chars) {
            //获取字符的ASCII编码
            int asciiCode = aChar;
            //偏移数据
            asciiCode *= key;
            asciiCode = asciiCode%128;
            //将偏移后的数据转为字符
            char result = (char)asciiCode;
            //拼接数据
            buffer.append(result);
        }
        return buffer.toString();
    }

    /**
     * 使用凯撒加密方式解密数据
     *
     * @param encryptedData :密文
     * @param key           :密钥
     * @return : 源数据
     */
    private static String decryptKaiser(String encryptedData, int key) {
        // 将字符串转为字符数组
        char[] chars = encryptedData.toCharArray();
        StringBuilder sb = new StringBuilder();
        // 遍历数组
        for (char aChar : chars) {
            // 获取字符的ASCII编码
            int asciiCode = aChar;
            // 偏移数据
            asciiCode -= key;
            // 将偏移后的数据转为字符
            char result = (char) asciiCode;
            // 拼接数据
            sb.append(result);
        }

        return sb.toString();
    }

    /*验证操作
    public static void main(String[] args) {
        String str = "open fire";
        String encode = encryptKaisa(str, 3);
        System.out.println("加密后："+encode);

        String decode = decryptKaiser(encode, 3);
        System.out.println("解密后："+decode);

    }*/

    /**
     * 用户注册接口
     * 接收参数统一使用字符串，接收后再进行类型转换
     *
     * @param name      姓名
     * @param password  密码
     * @return 通用返回对象
     */
    //定义请求的方式（POST而不是GET），以及表单上传编码格式（application/x-www-form-urlencoded）
    @RequestMapping(value = "/register", method = {RequestMethod.POST}, consumes = {CONTENT_TYPE_FORMED})
    //@RequestMapping(value = "/register", method = {RequestMethod.GET})    //测试用
    @ResponseBody
    public CommonReturnType register(@RequestParam(required=false, name="name")String name,
                                     @RequestParam(required=false, name="password")String password
    ) throws BusinessException{
        UserModel userModel = new UserModel();
        userModel.setName(name);
        //密码加密
        userModel.setEncrptPassword(encryptKaisa(password, 13));  //返回为空的密码
        //userModel.setEncrptPassword(password);   //明文存储密码

        Integer id = userService.register(userModel);
        userModel = userService.getUserById(id);
        UserVO userVO =  convertFromModel(userModel);

        return CommonReturnType.create(userVO);
    }

    //用户登录接口
    /**
     * 用户登录接口
     * 接收参数统一使用字符串，接收后再进行类型转换
     *
     * @param name      姓名
     * @param password  密码
     * @return 通用返回对象
     */
    @RequestMapping(value = "/login", method = {RequestMethod.POST}, consumes = {CONTENT_TYPE_FORMED})
    //@RequestMapping(value = "/login", method = {RequestMethod.GET})   //测试用
    @ResponseBody
    public CommonReturnType login(@RequestParam(required=false, name="name")String name,
                                  @RequestParam(required=false, name="password")String password)
            throws BusinessException{
        // 入参校验
        if (org.apache.commons.lang3.StringUtils.isEmpty(name)
                || org.apache.commons.lang3.StringUtils.isEmpty(password)
        ) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }

        // 用户登录服务，校验登录是否合法
        UserModel userModel = userService.validateLogin(name, encryptKaisa(password, 13));    //加密后
        //UserModel userModel = userService.validateLogin(name, password);   //未加密

        // 将登录凭证加入到用户登录成功的session内
        // 切换web页面的时候，可以不用重复登录
        session = httpServletRequest.getSession();
        session.setAttribute("IS_LOGIN", true);
        session.setAttribute("LOGIN_USER", userModel);

        UserVO userVO =  convertFromModel(userModel);

        // 登录成功，只返回success即可
        return CommonReturnType.create(userVO);
    }

    @RequestMapping(value = "/update", method = {RequestMethod.POST}, consumes = {CONTENT_TYPE_FORMED})
    //@RequestMapping(value = "/update")
    @ResponseBody
    public CommonReturnType userUpdate(@RequestParam(name="id")Integer id,
                                       @RequestParam(required=false, name="gender")Integer gender,
                                       @RequestParam(required=false, name="birthday")String birthdayString,
                                       @RequestParam(required=false, name="weight")Integer weight,
                                       @RequestParam(required=false, name="height")Integer height,
                                       @RequestParam(required=false, name="avoidance")Integer avoidance,
                                       @RequestParam(required=false, name="budget")Integer budget,
                                       @RequestParam(required=false, name="state")Integer state
                                       )
            throws BusinessException{
        // 入参校验

        //主体
        UserModel userModel = userService.getUserById(id);

        //若获取的对应用户信息不存在,报错
        if(userModel == null){
            throw new BusinessException(EmBusinessError.USER_NOT_EXIST);
        }

        userModel.setGender(gender);
        DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
        //DateFormat是抽象类 ，抽象类不可以直接创建对象，所以我们创建子类的对象
        Date birthday = new Date();
        try {
            birthday=df.parse(birthdayString);//这个格式必须按照上面给出的格式进行转化否则出错
        } catch (ParseException e) {
            e.printStackTrace();//为啥抛出异常的  不是随便的字符串都可以可以转化为日期吗的
        }
        userModel.setBirthday(birthday);
        userModel.setWeight(weight);
        userModel.setHeight(height);
        userModel.setAvoidance(avoidance);
        userModel.setBudget(budget);
        userModel.setState(state);

        userService.update(userModel);

        userModel = userService.getUserById(id);
        UserVO userVO =  convertFromModel(userModel);

        return CommonReturnType.create(userVO);
    }
}
