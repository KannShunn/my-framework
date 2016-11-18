package com.cesgroup.core.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.domain.Specification;

import com.google.common.collect.Lists;

public class DynamicSpecifications
{

	public static <T> Specification<T> bySearchFilter(final Collection<SearchFilter> filters, final Class<T> entityClazz)
	{
		return new Specification<T>()
		{
			@SuppressWarnings({ "unchecked", "rawtypes" })
			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder)
			{
				if (Collections3.isNotEmpty(filters))
				{

					List<Predicate> predicates = Lists.newArrayList();
					for (SearchFilter filter : filters)
					{
						// nested path translate, 如Task的名为"user.name"的filedName,
						// 转换为Task.user.name属性
						String[] names = StringUtils.split(filter.fieldName, ".");
						Path<String> expression = root.get(names[0]);
						for (int i = 1; i < names.length; i++)
						{
							expression = expression.get(names[i]);
						}

						// logic operator
						switch (filter.operator)
						{
						case EQ:
							predicates.add(builder.equal(expression, filter.value));
							break;
						case LIKE:
							predicates.add(builder.like(expression, "%" + filter.value + "%"));
							break;
						case GT:
							predicates.add(builder.greaterThan(expression, String.valueOf(filter.value)));
							break;
						case LT:
							predicates.add(builder.lessThan(expression, String.valueOf(filter.value)));
							break;
						case GTE:
							predicates.add(builder.greaterThanOrEqualTo(expression, String.valueOf(filter.value)));
							break;
						case LTE:
							predicates.add(builder.lessThanOrEqualTo(expression, String.valueOf(filter.value)));
							break;
						case NEQ:
							predicates.add(builder.notEqual(expression, (Comparable) filter.value));
							break;
						case IN:
							predicates.add(expression.in((filter.value.toString().split(","))));
							break;
						case IGCEQ:
							predicates.add(builder.equal(builder.upper(expression), filter.value.toString().toUpperCase()));
							break;
						}
					}

					// 将所有条件用 and 联合起来
					if (!predicates.isEmpty())
					{
						return builder.and(predicates.toArray(new Predicate[predicates.size()]));
					}
				}

				return builder.conjunction();
			}
		};
	}

	/**
	 * 解析形如 projectName_desc,age_asc 形式的排序条件
	 * 
	 * @param sorter
	 * @return projectName desc
	 */
	public static Sort parseSorter(String sorter)
	{
		List<Order> orders = new ArrayList<Order>();
		if (sorter != null && !"".equals(sorter))
		{
			// 首先循环逗号
			String[] order = sorter.split(",");
			Order or;
			for (String o : order)
			{
				if (o != null && !"".equals(o))
				{
					String[] field = o.split("_");
					if (field != null && field.length > 1)
					{
						if (field[0] != null && !"".equals(field[0]))
						{
							if (field[1].toLowerCase().equals("desc"))
							{
								or = new Order(Direction.DESC, field[0]);
							}
							else
							{
								or = new Order(Direction.ASC, field[0]);
							}
							if (or != null)
							{
								orders.add(or);
							}
						}
					}
				}
			}
		}
		return new Sort(orders);
	}
}
