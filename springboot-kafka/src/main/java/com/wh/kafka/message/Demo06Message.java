package com.wh.kafka.message;

/**
 * <p>
 *
 * </p>
 *
 * @author Wenhao Wang
 * @version 1.0.0
 * @date 2021/07/16
 */
public class Demo06Message {

	public static final String TOPIC = "DEMO_016";

	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Demo06Message{" +
				"id=" + id +
				'}';
	}
}
