package com.liang.ack.producer.callback;

import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class ConfirmSender implements RabbitTemplate.ConfirmCallback {

	private RabbitTemplate rabbitTemplate;

	/**
	 * 构造方法，注入rabbitTemplate
	 * @param rabbitTemplate
	 */
	public ConfirmSender(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
		// 设置消费回调
		this.rabbitTemplate.setConfirmCallback(this);
	}

	/**
	* 消息的回调，主要是实现RabbitTemplate.ConfirmCallback接口
	* 注意，消息回调只能代表成功消息发送到RabbitMQ服务器，不能代表消息被成功处理和接受
    * 如果消息没有到exchange,则confirm回调,ack=false
	* 如果消息到达exchange,则confirm回调,ack=true
	*/
	public void confirm(CorrelationData correlationData, boolean ack, String cause) {
		System.out.println("### ConfirmSend确认消息发送到exchange! 回调id:"
				+ correlationData);
		System.out.println(ack);
		if (ack) {
			System.out.println("### ConfirmSend确认消息发送到exchange! 消息成功消费");
		} else {
			System.out.println("### ConfirmSend确认消息发送到exchange! 消息消费失败:"
					+ cause + "\n重新发送");
		}
	}

}
