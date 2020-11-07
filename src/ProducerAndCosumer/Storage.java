package ProducerAndCosumer;


import java.util.LinkedList;

/**
 * Synchronized实现生产者-消费者
 */
//仓库函数，负责生成和消费产品
public class Storage {

    //maxsize of the warehouse
    private final int MAX_SIZE = 100;
    //warehouse of the produce
    private LinkedList<Object> list = new LinkedList<>();
    public void produce(int num){
        synchronized (list){
            //if the rest of the warehouse is not enough, and wait for consuming

            while( list.size() + num > MAX_SIZE ){
                System.out.println("[要生产的产品数量]:" + num + "\t[库存量]:" +
                        list.size() + "\t暂时不能执行生产任务");
                try{
                    list.wait();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
            System.out.println("[生产产品数量]:" + num + "\t[现有库存量]:" + list.size());
            for(int i=0;i<num;i++){

                list.add(new Object());
            }
            list.notifyAll();
        }
    }

    public void consume(int num){
        synchronized (list){
            while( list.size() < num ){
                System.out.println("[要消费的产品数量]:" + num + "\t[库存量]:" +
                        list.size() + "\t暂时不能执行消费任务");
                try{
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("[消费产品数量]:" + num + "\t[现有库存量]:" + list.size());
            for(int i=0;i<num;i++){
                list.remove();
            }
            list.notifyAll();
        }
    }

    public LinkedList<Object> getList()
    {
        return list;
    }

    public void setList(LinkedList<Object> list)
    {
        this.list = list;
    }

    public int getMAX_SIZE()
    {
        return MAX_SIZE;
    }


}
