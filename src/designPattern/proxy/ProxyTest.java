package designPattern.proxy;

interface Network {

    public void browse();
}

//被代理类
class Server implements Network {
    @Override
    public void browse() {
        System.out.println("Server is browsing the network");
    }
}

//代理类
class ProxyServer implements Network {
    private Network work;

    public ProxyServer(Network work) {
        this.work = work;
    }


    public void check() {
        System.out.println("Proxy is checking the network");
    }

    @Override
    public void browse() {
        check();
        work.browse();
        System.out.println("ProxyServer is browsing the network");
    }
}

public class ProxyTest {
    public static void main(String[] args) {
        Server server = new Server();
        ProxyServer proxy = new ProxyServer(server);
        proxy.browse();//实际上执行的是Server中的browse
    }
}
