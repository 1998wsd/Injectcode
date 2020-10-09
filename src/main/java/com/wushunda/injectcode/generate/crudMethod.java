package com.wushunda.injectcode.generate;

public interface crudMethod {
    /**
     * 插入单一行
     * @return
     */
    String create();

    /**
     * 插入多行
     * @return
     */
    String createBatch();

    /**
     * 查询单一行
     * @return
     */
    String retrieve();

    /**
     * 批量查询
     * @return
     */
    String retrieveBatch();

    /**
     * 分页查询
     */
    String retrievePagination();

    /**
     * 获取数量
     * @return 数量
     */
    String count();
    /**
     * 更新单一行
     * @return
     */
    String update();

    /**
     * 批量更新
     * @return
     */
    String updateBatch();

    /**
     * 根据Id删除
     * @return
     */
    String deleteById();

    /**
     * 根据Id批量删除
     * @return
     */
    String deleteByIdBatch();

    /**
     * 全匹配删除
     * @return
     */
    String delete();
}
