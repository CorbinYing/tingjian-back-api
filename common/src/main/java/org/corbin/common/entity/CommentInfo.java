package org.corbin.common.entity;

import lombok.Data;
import org.corbin.common.base.entity.AbstractBaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Data
@DynamicInsert
@DynamicUpdate
@EntityListeners(AuditingEntityListener.class)
@Table(name = "comment_info")
public class CommentInfo extends AbstractBaseEntity  implements Serializable {

    @Column(name = "comment_id")
    private Long commentId;

    @Column(name = "comment_content")
    private String commentContent;

    @Column(name = "comment_star")
    private Integer commentStar;

    @Column(name = "user_id")
    private Long userId;


//    public CommentInfo(){
//        super();
//        this.commentId= IdHelper.snowflake.nextId2Long();
//    }
}
