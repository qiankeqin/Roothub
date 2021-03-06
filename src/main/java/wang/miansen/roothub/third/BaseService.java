package wang.miansen.roothub.third;

/**
 * 第三方集成服务接口
 * 
 * @author: miansen.wang
 * @date: 2019-03-10
 */
public interface BaseService<T> {

	/**
	 * 外接服务初始化实例方法
	 * @return
	 */
	T instance();
}
