package io.github.edmaputra.ed.core.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QBaseIdEntity is a Querydsl query type for BaseIdEntity
 */
@Generated("com.querydsl.codegen.SupertypeSerializer")
public class QBaseIdEntity extends EntityPathBase<BaseIdEntity<? extends java.io.Serializable>> {

    private static final long serialVersionUID = -1213991110L;

    public static final QBaseIdEntity baseIdEntity = new QBaseIdEntity("baseIdEntity");

    public final QBaseEntity _super = new QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.Instant> createTime = _super.createTime;

    //inherited
    public final StringPath creator = _super.creator;

    //inherited
    public final BooleanPath deleteFlag = _super.deleteFlag;

    public final SimplePath<java.io.Serializable> id = createSimple("id", java.io.Serializable.class);

    //inherited
    public final StringPath updater = _super.updater;

    //inherited
    public final DateTimePath<java.time.Instant> updateTime = _super.updateTime;

    //inherited
    public final StringPath version = _super.version;

    @SuppressWarnings({"all", "rawtypes", "unchecked"})
    public QBaseIdEntity(String variable) {
        super((Class) BaseIdEntity.class, forVariable(variable));
    }

    @SuppressWarnings({"all", "rawtypes", "unchecked"})
    public QBaseIdEntity(Path<? extends BaseIdEntity> path) {
        super((Class) path.getType(), path.getMetadata());
    }

    @SuppressWarnings({"all", "rawtypes", "unchecked"})
    public QBaseIdEntity(PathMetadata metadata) {
        super((Class) BaseIdEntity.class, metadata);
    }

}

