package com.nowcoder.community;

import com.nowcoder.community.dao.AlphaDao;
import com.nowcoder.community.service.AlphaService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;

/*
controller处理浏览器的请求，处理过程中调用业务组件service处理业务，
业务组件service调用dao访问数据库，彼此互相依赖
彼此的依赖使用依赖注入实现
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
//让test使用CommunityApplication配置类
class CommunityApplicationTests implements ApplicationContextAware {
//哪个类想实现spring容器就实现接口ApplicationContextAware
//其中有个方法需要实现：setApplicationContext 参数ApplicationContext就是spring容器

	private ApplicationContext applicationContext;
	//记录spring容器

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
		//容器被传进来
	}

	@Test
	public void testApplicationContext(){
		System.out.println(applicationContext);//测试spring容器 打印

		//从容器里获取自动装配的bean AlphaDao类型的bean 依赖的不是bean本身，而是bean的接口
		AlphaDao alphaDao = applicationContext.getBean(AlphaDao.class);
		//如果按照类型去获取bean，满足条件的有两个 alphaDaoHibernateImpl和AlphaDaoMyBatisImpl
		//在希望获取到的bean前再加一个注解@Primary,则此bean会被优先装配，实现类变了也不影响
		System.out.println(alphaDao.select());

		//通过名字强行获取非优先的bean (名字,类型)
		alphaDao = applicationContext.getBean("alphaHibernate",AlphaDao.class);
		System.out.println(alphaDao.select());

	}

	//测试bean的管理方式
	@Test
	public void testBeanManagement(){
		AlphaService alphaService = applicationContext.getBean(AlphaService.class);//按照类型获取bean
		System.out.println(alphaService);

		 alphaService = applicationContext.getBean(AlphaService.class);//按照类型获取bean
		System.out.println(alphaService);
	}

	@Test
	public void testBeanConfig(){
		SimpleDateFormat simpleDateFormat = applicationContext.getBean(SimpleDateFormat.class);
		System.out.println(simpleDateFormat.format(new Date()));
	}

	@Autowired//依赖注入
	@Qualifier("alphaHibernate")//指明要alphaHibernate的bean
	private AlphaDao alphaDao;//spring容器把AlphaDao注入这个alphaDao属性

	@Autowired
	private AlphaService alphaService;//spring容器把AlphaService注入这个alphaService属性

	@Autowired
	private SimpleDateFormat simpleDateFormat;
	@Test
	public void testDI(){//DI:dependency Injection
		System.out.println(alphaDao);
		System.out.println(alphaService);
		System.out.println(simpleDateFormat);
	}

}
