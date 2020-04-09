package com.liang.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NioServer {

    public static void main(String[] args) throws IOException {

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        Selector selector = Selector.open();

        serverSocketChannel.socket().bind(new InetSocketAddress(6666));

        serverSocketChannel.configureBlocking(false);

        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        Set<SelectionKey> selectionKeys = selector.selectedKeys();

        Iterator<SelectionKey> selectionKeyIterator = selectionKeys.iterator();

        while (selectionKeyIterator.hasNext()){

            SelectionKey selectionKey = selectionKeyIterator.next();

            if(selectionKey.isAcceptable()){

                SocketChannel socketChannel = serverSocketChannel.accept();

                socketChannel.register(selector,SelectionKey.OP_READ, ByteBuffer.allocate(1024));


            }

        }

    }
}
