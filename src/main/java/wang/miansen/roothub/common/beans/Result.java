package wang.miansen.roothub.common.beans;

/**
 * 封装JSON对象，所有的返回结果都使用它
 * @author sen
 * 2018年5月16日
 * 下午7:14:57
 * @param <T>
 * TODO
 */
public class Result<T> {

	private String code;
	private boolean success;//是否成功标志
	private T data;//成功时返回的对象
	private String error;//错误信息
	public Result() {
		
	}

	public Result(String code, boolean success) {
		this.code = code;
		this.success = success;
	}



	// 成功时的构造器
	public Result(String code, boolean success, T data) {
		this.code = code;
		this.success = success;
		this.data = data;
	}

	// 失败时的构造器
	public Result(String code, boolean success, String error) {
		this.code = code;
		this.success = success;
		this.error = error;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	@Override
	public String toString() {
		return "Result [success=" + success + ", data=" + data + ", error=" + error + "]";
	}
	
}
