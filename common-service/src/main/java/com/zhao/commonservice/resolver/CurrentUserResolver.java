package com.zhao.commonservice.resolver;

import com.zhao.common.modal.TokenModel;
import com.zhao.common.utils.JwtTokenUtil;
import com.zhao.commonservice.annotation.CurrentUser;
import com.zhao.commonservice.constants.SysConstants;
import com.zhao.doraclients.client.AuthServiceClient;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.util.function.Function;

public class CurrentUserResolver implements HandlerMethodArgumentResolver {

	private Function<HttpServletRequest, Object> currentUserHandler = null;

	private AuthServiceClient authServiceClient;

	public CurrentUserResolver(AuthServiceClient authServiceClient){
		this.authServiceClient = authServiceClient;
	}

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.hasParameterAnnotation(CurrentUser.class);
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
		HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
		if (currentUserHandler != null){
			return currentUserHandler.apply(request);
		} else {
			if (request == null)
				return null;

			if (!JwtTokenUtil.ready){
				JwtTokenUtil.initConfig(authServiceClient.getTokenConfig().getContent());
			}
			TokenModel tokenModel = JwtTokenUtil.token2tokenModal(request.getHeader(SysConstants.TOKEN));
			return tokenModel == null ? null : tokenModel.getUser();
		}
	}


	public CurrentUserResolver setCurrentUserHandler(Function<HttpServletRequest, Object> currentUserHandler) {
		this.currentUserHandler = currentUserHandler;
		return this;
	}
}
