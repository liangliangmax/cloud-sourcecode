package com.liang.ack.producer.callback;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class ReturnSender implements RabbitTemplate.ReturnCallback {

	private RabbitTemplate rabbitTemplate;

	/**
	 * 构造方法，注入rabbitTemplate
	 * @param rabbitTemplate
	 */
	public ReturnSender(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
		// 设置消费回调
		this.rabbitTemplate.setReturnCallback(this);
	}

	/**
	 * exchange到queue成功,则不回调return
	 * exchange到queue失败,则回调return(需设置mandatory=true,否则不回回调,消息就丢了)
	 * @param message
	 * @param replyCode
	 * @param replyText
	 * @param exchange
	 * @param routingKey
	 */
	@Override
	public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
		System.out.println("^^^^^^ returnedMessage确认消息发送到routingKey! routingKey:" + routingKey + ",message=" + message.toString());
		System.out.println("++++++++++++"+message+ "======="+ replyCode + "========"+ exchange + "=========="+ routingKey);
	}


}
