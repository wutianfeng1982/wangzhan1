package com.maike.myblog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author geekcjj
 * @since 2019-10-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "LeaveWords对象", description = "")
public class LeaveWords implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId(value = "id", type = IdType.INPUT)
	private String id;

	@ApiModelProperty(value = "用户留言的昵称")
	@TableField("nick_name")
	private String nickName;

	@ApiModelProperty(value = "用户的email")
	@TableField("email")
	private String email;

	@ApiModelProperty(value = "用户的个人网址")
	@TableField("website")
	private String website;

	@ApiModelProperty(value = "用户留言的内容")
	@TableField("content")
	private String content;

	@ApiModelProperty(value = "创建时间")
	@TableField("create_time")
	private LocalDateTime createTime;

	@ApiModelProperty(value = "留言的类型")
	@TableField("lw_type")
	private String lwType;
	
	@ApiModelProperty(value = "留言的对象用户ID")
	@TableField("to_user_id")
	private String toUserId;
	@ApiModelProperty(value = "留言的对象用户昵称")
	@TableField("to_nick_name")
	private String toNickName;

}
