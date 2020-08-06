package tb_inspection.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import tb_inspection.constant.ResultConstant;
import tb_inspection.entity.CommonResult;

@ControllerAdvice
@ResponseBody
public class AllExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(AllExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    public CommonResult handlerUnexcptedServer(Exception ex) {
    	ex.printStackTrace();
        logger.error("发生系统异常，错误信息为：" + ex.getMessage());
        return new CommonResult(ResultConstant.FAIL_CODE, ex.getMessage());
    }
}
