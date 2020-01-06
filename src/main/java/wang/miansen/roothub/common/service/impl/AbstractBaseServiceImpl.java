package wang.miansen.roothub.common.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.transaction.annotation.Transactional;

import wang.miansen.roothub.common.entity.BaseDO;
import wang.miansen.roothub.common.dto.BaseDTO;
import wang.miansen.roothub.common.service.BaseService;
import wang.miansen.roothub.common.dao.mapper.wrapper.query.QueryWrapper;
import wang.miansen.roothub.common.dao.mapper.wrapper.update.UpdateWrapper;

/**
 * 该类是 Service impl 层的基础父类，实现了常用的业务增删改查方法，建议大部分的 Service impl 层继承。
 * <p>继承该类后即可拥有简单的业务 CRUD 能力。
 * 
 * @param <DO> 数据库表映射实体类的类型
 * @param <DTO> 数据传输的类型
 * 
 * @author miansen.wang
 * @date 2019-12-29
 * @since 3.0
 */
public abstract class AbstractBaseServiceImpl<DO extends BaseDO, DTO extends BaseDTO> implements BaseService<DO, DTO> {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public boolean save(DTO dto) {
		return retBool(getDao().insert(getDTO2DOMapper().apply(dto)));
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean saveBatch(Collection<DTO> dtoList) {
		dtoList.forEach(dto -> save(dto));
		return true;
	}

	@Override
	public boolean remove(UpdateWrapper<DO> updateWrapper) {
		return retBool(getDao().delete(updateWrapper));
	}

	@Override
	public boolean removeById(Serializable id) {
		return retBool(getDao().deleteById(id));
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean removeBatchIds(Collection<? extends Serializable> idList) {
		idList.forEach(id -> removeById(id));
		return true;
	}

	@Override
	public boolean update(DTO dto, UpdateWrapper<DO> updateWrapper) {
		return retBool(getDao().update(getDTO2DOMapper().apply(dto), updateWrapper));
	}

	@Override
	public boolean updateById(DTO dto) {
		return retBool(getDao().updateById(getDTO2DOMapper().apply(dto)));
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean updateBatchIds(Collection<DTO> dtoList) {
		dtoList.forEach(dto -> updateById(dto));
		return true;
	}

	@Override
	public DTO getById(Serializable id) {
		return getDO2DTOMapper().apply(getDao().selectById(id));
	}

	@Override
	public DTO getOne(QueryWrapper<DO> queryWrapper) {
		return getDO2DTOMapper().apply(getDao().selectOne(queryWrapper));
	}

	@Override
	public Integer count() {
		return getDao().selectCount(null);
	}

	@Override
	public Integer count(QueryWrapper<DO> queryWrapper) {
		return getDao().selectCount(queryWrapper);
	}

	@Override
	public List<DTO> list() {
		return getDao().selectList(null).stream().map(getDO2DTOMapper()).collect(Collectors.toList());
	}

	@Override
	public List<DTO> list(QueryWrapper<DO> queryWrapper) {
		return getDao().selectList(queryWrapper).stream().map(getDO2DTOMapper()).collect(Collectors.toList());
	}

	@Override
	public List<DTO> listBatchIds(Collection<? extends Serializable> idList) {
		return getDao().selectBatchIds(idList).stream().map(getDO2DTOMapper()).collect(Collectors.toList());
	}

	/**
	 * 判断数据库操作是否成功
	 * @param result  数据库操作返回影响条数
	 * @return boolean
	 */
	private static boolean retBool(Integer result) {
		return result != null && result >= 1;
	}

}