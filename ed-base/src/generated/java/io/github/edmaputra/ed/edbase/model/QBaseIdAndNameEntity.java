package io.github.edmaputra.ed.edbase.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QBaseIdAndNameEntity is a Querydsl query type for BaseIdAndNameEntity
 */
@Generated("com.querydsl.codegen.SupertypeSerializer")
public class QBaseIdAndNameEntity extends EntityPathBase<BaseIdAndNameEntity<? extends java.io.Serializable>> {

    private static final long serialVersionUID = -721839329L;

    public static final QBaseIdAndNameEntity baseIdAndNameEntity = new QBaseIdAndNameEntity("baseIdAndNameEntity");

    public final QBaseIdEntity _super = new QBaseIdEntity(this);

    //inherited
    public final DateTimePath<java.time.ZonedDateTime> createTime = _super.createTime;

    //inherited
    public final StringPath creator = _super.creator;

    //inherited
    public final StringPath deleteBy = _super.deleteBy;

    //inherited
    public final BooleanPath deleteFlag = _super.deleteFlag;

    //inherited
    public final DateTimePath<java.time.ZonedDateTime> deleteTime = _super.deleteTime;

    //inherited
    public final SimplePath<java.io.Serializable> id = _super.id;

    public final StringPath name = createString("name");

    //inherited
    public final StringPath updater = _super.updater;

    //inherited
    public final DateTimePath<java.time.ZonedDateTime> updateTime = _super.updateTime;

    //inherited
    public final StringPath version = _super.version;

    @SuppressWarnings({"all", "rawtypes", "unchecked"})
    public QBaseIdAndNameEntity(String variable) {
        super((Class) BaseIdAndNameEntity.class, forVariable(variable));
    }

    @SuppressWarnings({"all", "rawtypes", "unchecked"})
    public QBaseIdAndNameEntity(Path<? extends BaseIdAndNameEntity> path) {
        super((Class) path.getType(), path.getMetadata());
    }

    @SuppressWarnings({"all", "rawtypes", "unchecked"})
    public QBaseIdAndNameEntity(PathMetadata metadata) {
        super((Class) BaseIdAndNameEntity.class, metadata);
    }

}

