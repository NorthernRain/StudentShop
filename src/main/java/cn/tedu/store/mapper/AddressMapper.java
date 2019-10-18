package cn.tedu.store.mapper;

import cn.tedu.store.entity.Address;

/**
 * @author LeafDust
 * @create 2019-10-18 16:00
 */
public interface AddressMapper {
    /**
     * 插入用户收货地址
     *
     * @param address 用户收货地址
     * @return 操作成功的条数
     */
    Integer insert(Address address);

    /**
     * 统计用户存在的收货地址条数
     * @param uid 用户uid
     * @return 操作成功的条数
     */
    Integer countAddressByUid(Integer uid);
}
