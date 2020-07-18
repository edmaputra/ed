package io.github.edmaputra.ed.core.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QBaseEntity is a Querydsl query type for BaseEntity
 */
@Generated("com.querydsl.codegen.SupertypeSerializer")
public class QBaseEntity extends EntityPathBase<BaseEntity> {

    private static final long serialVersionUID = 256861503L;

    public static final QBaseEntity baseEntity = new QBaseEntity("baseEntity");

    public final DateTimePath<java.time.Instant> createTime = createDateTime("createTime", java.time.Instant.class);

    public final StringPath creator = createString("creator");

    public final BooleanPath deleteFlag = createBoolean("deleteFlag");

    public final StringPath updater = createString("updater");

    public final DateTimePath<java.time.Instant> updateTime = createDateTime("updateTime", java.time.Instant.class);

    public final StringPath version = createString("version");

    public QBaseEntity(String variable) {
        super(BaseEntity.class, forVariable(variable));
    }

    public QBaseEntity(Path<? extends BaseEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBaseEntity(PathMetadata metadata) {
        super(BaseEntity.class, metadata);
    }

}

