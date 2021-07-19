package com.wh.kafka.message;

/**
 * <p>
 *
 * </p>
 *
 * @author Wenhao Wang
 * @version 1.0.0
 * @date 2021/07/15
 */
public class Demo01Message {

	public static final String TOPIC = "DEMO_01";

	/**
	 * 编号
	 */
	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Demo01Message{" +
				"id=" + id +
				'}';
	}
}
