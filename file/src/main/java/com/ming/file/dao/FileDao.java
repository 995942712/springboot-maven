package com.ming.file.dao;

import com.ming.file.domain.FileInfo;
import org.apache.ibatis.annotations.*;
import java.util.List;

/**
 * 文件管理Dao
 */
@Mapper
public interface FileDao {

    /**
     * 查询全部文件
     * @return
     */
    @Select("select id,name,old_name oldName,size,suffix,path,url from sys_file")
    List<FileInfo> findAll();

    /**
     * 根据id查询文件
     * @param id
     * @return
     */
    @Select("select id,name,old_name oldName,size,suffix,path,url from sys_file where id=#{id}")
    FileInfo findById(@Param("id") Integer id);

    /**
     * 根据name查询文件
     * @param oldName
     * @return
     */
    @Select("select id,name,old_name oldName,size,suffix,path,url from sys_file where old_name like concat('%',#{oldName},'%')")
    FileInfo findByName(@Param("oldName") String oldName);

    /**
     * 保存文件
     * @return
     */
    @Insert("insert into sys_file(name,old_name,size,suffix,path,url) values(#{name},#{oldName},#{size},#{suffix},#{path},#{url})")
    int save(FileInfo file);

    /**
     * 更新文件
     * @return
     */
    @Update("update sys_file set old_name=#{old_name},suffix=#{suffix},path=#{path},#{url} where id=#{id}")
    int update(FileInfo file);

    /**
     * 删除文件
     * @return
     */
    @Delete("delete from sys_file where id=#{id}")
    int delete(@Param("id") Integer id);

}