package cn.davickk.rdi.essentials.general.thread.server;

import cn.davickk.rdi.essentials.RDIEssentials;

public class SQLReconnT extends Thread{
    public void run(){

            RDIEssentials.createSQLConnection();
    }
}
