package com.example.helloworld;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP.Basic.RecoverOk;
import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.AMQP.Exchange.BindOk;
import com.rabbitmq.client.AMQP.Exchange.DeclareOk;
import com.rabbitmq.client.AMQP.Exchange.DeleteOk;
import com.rabbitmq.client.AMQP.Exchange.UnbindOk;
import com.rabbitmq.client.AMQP.Queue.PurgeOk;
import com.rabbitmq.client.AMQP.Tx.CommitOk;
import com.rabbitmq.client.AMQP.Tx.RollbackOk;
import com.rabbitmq.client.AMQP.Tx.SelectOk;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Command;
import com.rabbitmq.client.ConfirmListener;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.FlowListener;
import com.rabbitmq.client.GetResponse;
import com.rabbitmq.client.Method;
import com.rabbitmq.client.ReturnListener;
import com.rabbitmq.client.ShutdownListener;
import com.rabbitmq.client.ShutdownSignalException;

public class CloseableChannel implements Channel, AutoCloseable {

	private final Channel delegate;

	public CloseableChannel(Channel channel) {
		delegate = channel;
	}

	public void addShutdownListener(ShutdownListener listener) {
		delegate.addShutdownListener(listener);
	}

	public void removeShutdownListener(ShutdownListener listener) {
		delegate.removeShutdownListener(listener);
	}

	public ShutdownSignalException getCloseReason() {
		return delegate.getCloseReason();
	}

	public void notifyListeners() {
		delegate.notifyListeners();
	}

	public boolean isOpen() {
		return delegate.isOpen();
	}

	public int getChannelNumber() {
		return delegate.getChannelNumber();
	}

	public Connection getConnection() {
		return delegate.getConnection();
	}

	public void close() throws IOException {
		delegate.close();
	}

	public void close(int closeCode, String closeMessage) throws IOException {
		delegate.close(closeCode, closeMessage);
	}

	public boolean flowBlocked() {
		return delegate.flowBlocked();
	}

	public void abort() throws IOException {
		delegate.abort();
	}

	public void abort(int closeCode, String closeMessage) throws IOException {
		delegate.abort(closeCode, closeMessage);
	}

	public void addReturnListener(ReturnListener listener) {
		delegate.addReturnListener(listener);
	}

	public boolean removeReturnListener(ReturnListener listener) {
		return delegate.removeReturnListener(listener);
	}

	public void clearReturnListeners() {
		delegate.clearReturnListeners();
	}

	public void addFlowListener(FlowListener listener) {
		delegate.addFlowListener(listener);
	}

	public boolean removeFlowListener(FlowListener listener) {
		return delegate.removeFlowListener(listener);
	}

	public void clearFlowListeners() {
		delegate.clearFlowListeners();
	}

	public void addConfirmListener(ConfirmListener listener) {
		delegate.addConfirmListener(listener);
	}

	public boolean removeConfirmListener(ConfirmListener listener) {
		return delegate.removeConfirmListener(listener);
	}

	public void clearConfirmListeners() {
		delegate.clearConfirmListeners();
	}

	public Consumer getDefaultConsumer() {
		return delegate.getDefaultConsumer();
	}

	public void setDefaultConsumer(Consumer consumer) {
		delegate.setDefaultConsumer(consumer);
	}

	public void basicQos(int prefetchSize, int prefetchCount, boolean global) throws IOException {
		delegate.basicQos(prefetchSize, prefetchCount, global);
	}

	public void basicQos(int prefetchCount, boolean global) throws IOException {
		delegate.basicQos(prefetchCount, global);
	}

	public void basicQos(int prefetchCount) throws IOException {
		delegate.basicQos(prefetchCount);
	}

	public void basicPublish(String exchange, String routingKey, BasicProperties props, byte[] body) throws IOException {
		delegate.basicPublish(exchange, routingKey, props, body);
	}

	public void basicPublish(String exchange, String routingKey, boolean mandatory, BasicProperties props, byte[] body)
			throws IOException {
		delegate.basicPublish(exchange, routingKey, mandatory, props, body);
	}

	public void basicPublish(String exchange, String routingKey, boolean mandatory, boolean immediate,
			BasicProperties props, byte[] body) throws IOException {
		delegate.basicPublish(exchange, routingKey, mandatory, immediate, props, body);
	}

	public DeclareOk exchangeDeclare(String exchange, String type) throws IOException {
		return delegate.exchangeDeclare(exchange, type);
	}

	public DeclareOk exchangeDeclare(String exchange, String type, boolean durable) throws IOException {
		return delegate.exchangeDeclare(exchange, type, durable);
	}

	public DeclareOk exchangeDeclare(String exchange, String type, boolean durable, boolean autoDelete,
			Map<String, Object> arguments) throws IOException {
		return delegate.exchangeDeclare(exchange, type, durable, autoDelete, arguments);
	}

	public DeclareOk exchangeDeclare(String exchange, String type, boolean durable, boolean autoDelete,
			boolean internal, Map<String, Object> arguments) throws IOException {
		return delegate.exchangeDeclare(exchange, type, durable, autoDelete, internal, arguments);
	}

	public DeclareOk exchangeDeclarePassive(String name) throws IOException {
		return delegate.exchangeDeclarePassive(name);
	}

	public DeleteOk exchangeDelete(String exchange, boolean ifUnused) throws IOException {
		return delegate.exchangeDelete(exchange, ifUnused);
	}

	public DeleteOk exchangeDelete(String exchange) throws IOException {
		return delegate.exchangeDelete(exchange);
	}

	public BindOk exchangeBind(String destination, String source, String routingKey) throws IOException {
		return delegate.exchangeBind(destination, source, routingKey);
	}

	public BindOk exchangeBind(String destination, String source, String routingKey, Map<String, Object> arguments)
			throws IOException {
		return delegate.exchangeBind(destination, source, routingKey, arguments);
	}

	public UnbindOk exchangeUnbind(String destination, String source, String routingKey) throws IOException {
		return delegate.exchangeUnbind(destination, source, routingKey);
	}

	public UnbindOk exchangeUnbind(String destination, String source, String routingKey, Map<String, Object> arguments)
			throws IOException {
		return delegate.exchangeUnbind(destination, source, routingKey, arguments);
	}

	public com.rabbitmq.client.AMQP.Queue.DeclareOk queueDeclare() throws IOException {
		return delegate.queueDeclare();
	}

	public com.rabbitmq.client.AMQP.Queue.DeclareOk queueDeclare(String queue, boolean durable, boolean exclusive,
			boolean autoDelete, Map<String, Object> arguments) throws IOException {
		return delegate.queueDeclare(queue, durable, exclusive, autoDelete, arguments);
	}

	public com.rabbitmq.client.AMQP.Queue.DeclareOk queueDeclarePassive(String queue) throws IOException {
		return delegate.queueDeclarePassive(queue);
	}

	public com.rabbitmq.client.AMQP.Queue.DeleteOk queueDelete(String queue) throws IOException {
		return delegate.queueDelete(queue);
	}

	public com.rabbitmq.client.AMQP.Queue.DeleteOk queueDelete(String queue, boolean ifUnused, boolean ifEmpty)
			throws IOException {
		return delegate.queueDelete(queue, ifUnused, ifEmpty);
	}

	public com.rabbitmq.client.AMQP.Queue.BindOk queueBind(String queue, String exchange, String routingKey)
			throws IOException {
		return delegate.queueBind(queue, exchange, routingKey);
	}

	public com.rabbitmq.client.AMQP.Queue.BindOk queueBind(String queue, String exchange, String routingKey,
			Map<String, Object> arguments) throws IOException {
		return delegate.queueBind(queue, exchange, routingKey, arguments);
	}

	public com.rabbitmq.client.AMQP.Queue.UnbindOk queueUnbind(String queue, String exchange, String routingKey)
			throws IOException {
		return delegate.queueUnbind(queue, exchange, routingKey);
	}

	public com.rabbitmq.client.AMQP.Queue.UnbindOk queueUnbind(String queue, String exchange, String routingKey,
			Map<String, Object> arguments) throws IOException {
		return delegate.queueUnbind(queue, exchange, routingKey, arguments);
	}

	public PurgeOk queuePurge(String queue) throws IOException {
		return delegate.queuePurge(queue);
	}

	public GetResponse basicGet(String queue, boolean autoAck) throws IOException {
		return delegate.basicGet(queue, autoAck);
	}

	public void basicAck(long deliveryTag, boolean multiple) throws IOException {
		delegate.basicAck(deliveryTag, multiple);
	}

	public void basicNack(long deliveryTag, boolean multiple, boolean requeue) throws IOException {
		delegate.basicNack(deliveryTag, multiple, requeue);
	}

	public void basicReject(long deliveryTag, boolean requeue) throws IOException {
		delegate.basicReject(deliveryTag, requeue);
	}

	public String basicConsume(String queue, Consumer callback) throws IOException {
		return delegate.basicConsume(queue, callback);
	}

	public String basicConsume(String queue, boolean autoAck, Consumer callback) throws IOException {
		return delegate.basicConsume(queue, autoAck, callback);
	}

	public String basicConsume(String queue, boolean autoAck, Map<String, Object> arguments, Consumer callback)
			throws IOException {
		return delegate.basicConsume(queue, autoAck, arguments, callback);
	}

	public String basicConsume(String queue, boolean autoAck, String consumerTag, Consumer callback) throws IOException {
		return delegate.basicConsume(queue, autoAck, consumerTag, callback);
	}

	public String basicConsume(String queue, boolean autoAck, String consumerTag, boolean noLocal, boolean exclusive,
			Map<String, Object> arguments, Consumer callback) throws IOException {
		return delegate.basicConsume(queue, autoAck, consumerTag, noLocal, exclusive, arguments, callback);
	}

	public void basicCancel(String consumerTag) throws IOException {
		delegate.basicCancel(consumerTag);
	}

	public RecoverOk basicRecover() throws IOException {
		return delegate.basicRecover();
	}

	public RecoverOk basicRecover(boolean requeue) throws IOException {
		return delegate.basicRecover(requeue);
	}

	@SuppressWarnings("deprecation")
	public void basicRecoverAsync(boolean requeue) throws IOException {
		delegate.basicRecoverAsync(requeue);
	}

	public SelectOk txSelect() throws IOException {
		return delegate.txSelect();
	}

	public CommitOk txCommit() throws IOException {
		return delegate.txCommit();
	}

	public RollbackOk txRollback() throws IOException {
		return delegate.txRollback();
	}

	public com.rabbitmq.client.AMQP.Confirm.SelectOk confirmSelect() throws IOException {
		return delegate.confirmSelect();
	}

	public long getNextPublishSeqNo() {
		return delegate.getNextPublishSeqNo();
	}

	public boolean waitForConfirms() throws InterruptedException {
		return delegate.waitForConfirms();
	}

	public boolean waitForConfirms(long timeout) throws InterruptedException, TimeoutException {
		return delegate.waitForConfirms(timeout);
	}

	public void waitForConfirmsOrDie() throws IOException, InterruptedException {
		delegate.waitForConfirmsOrDie();
	}

	public void waitForConfirmsOrDie(long timeout) throws IOException, InterruptedException, TimeoutException {
		delegate.waitForConfirmsOrDie(timeout);
	}

	public void asyncRpc(Method method) throws IOException {
		delegate.asyncRpc(method);
	}

	public Command rpc(Method method) throws IOException {
		return delegate.rpc(method);
	}
}
