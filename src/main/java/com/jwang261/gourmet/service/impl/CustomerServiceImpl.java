package com.jwang261.gourmet.service.impl;

import com.jwang261.gourmet.dao.CustomerDao;
import com.jwang261.gourmet.dao.RecordDao;
import com.jwang261.gourmet.pojo.entity.Customer;
import com.jwang261.gourmet.pojo.entity.CustomerOrder;
import com.jwang261.gourmet.pojo.entity.Record;
import com.jwang261.gourmet.service.CustomerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service //Give to SpringIOC
public class CustomerServiceImpl implements CustomerService {

    @Resource
    CustomerDao customerDao;

    @Resource
    RecordDao recordDao;

    @Override
    public Customer login(Customer customer) {

        Customer realCustomer = customerDao.loginCheck(customer);


        return realCustomer;



    }



    @Override
    public int insert (Customer customer) {
        customerDao.insert(customer);

        return customerDao.selectNewId();
    }

    @Override
    public boolean canCreate(Customer customer) {
        List<Customer> list = customerDao.checkRegister();
        for(Customer c : list){
            if(c.getEmail().equals(customer.getEmail()) || c.getPhoneNum().equals(customer.getPhoneNum())){
                return false;
            }
        }

        return true;
    }

    @Override
    public Map<CustomerOrder.State,List<CustomerOrder>> getCustomerOrders(String phoneNum) {

        Map<CustomerOrder.State, List<CustomerOrder>> map = new HashMap<>();

        List<Record> records = recordDao.selectByCustomerPhoneNum(phoneNum);

        for (Record r: records) {
            CustomerOrder co = new CustomerOrder();
            switch (r.getState()){
                case 0:
                    insertCustomerOrder(map, r, CustomerOrder.State.CANCELED,"Canceled");
                    break;
                case 1:
                    insertCustomerOrder(map, r, CustomerOrder.State.PlACED,"Placed");
                    break;
                case 2:
                    insertCustomerOrder(map, r, CustomerOrder.State.PREPARING,"Preparing");
                    break;
                case 3:
                    insertCustomerOrder(map, r, CustomerOrder.State.ONTHEWAY,"On the way");
                    break;
                case 4:
                    insertCustomerOrder(map, r, CustomerOrder.State.CONFIRMED,"Confirmed");
                    break;
                case 5:
                    insertCustomerOrder(map, r, CustomerOrder.State.COMMENT,"Reviewed");
                    break;
                case -1:
                    insertCustomerOrder(map, r, CustomerOrder.State.REQUESTREFUND,"Requesting Refund");
                    break;
                case -2:
                    insertCustomerOrder(map, r, CustomerOrder.State.REQUESTOK,"Refund Approved");
                    break;


            }
        }


        return map;
    }

    public void insertCustomerOrder(Map<CustomerOrder.State,List<CustomerOrder>> map, Record record, CustomerOrder.State state, String stateStr){

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        CustomerOrder co = new CustomerOrder();
        co.setRecordId(record.getId());
        co.setState(state);
        co.setDriverName(record.getDriverName());
        co.setOrderDate(sdf.format(record.getOrderDate()));
        co.setTotalPrice(record.getTotalPrice());
        co.setStateStr(stateStr);
        if(map.containsKey(state)){
            map.get(state).add(co);
        }else{
            List<CustomerOrder> list = new ArrayList<>();
            list.add(co);

            map.put(state,list);
        }
    }
}
