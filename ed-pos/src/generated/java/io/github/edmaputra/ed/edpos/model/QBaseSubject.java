package io.github.edmaputra.ed.edpos.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBaseSubject is a Querydsl query type for BaseSubject
 */
@Generated("com.querydsl.codegen.SupertypeSerializer")
public class QBaseSubject extends EntityPathBase<BaseSubject<? extends java.io.Serializable, ? extends io.github.edmaputra.ed.edbase.model.BaseAddress>> {

    private static final long serialVersionUID = 1254239316L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBaseSubject baseSubject = new QBaseSubject("baseSubject");

    public final io.github.edmaputra.ed.edbase.model.QBaseIdAndNameEntity _super = new io.github.edmaputra.ed.edbase.model.QBaseIdAndNameEntity(this);

    public final io.github.edmaputra.ed.edbase.model.QBaseAddress address;

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

    public final StringPath email = createString("email");

    //inherited
    public final SimplePath<java.io.Serializable> id = _super.id;

    //inherited
    public final StringPath name = _super.name;

    //inherited
    public final StringPath note = _super.note;

    public final StringPath phone = createString("phone");

    //inherited
    public final StringPath updater = _super.updater;

    //inherited
    public final DateTimePath<java.time.ZonedDateTime> updateTime = _super.updateTime;

    //inherited
    public final StringPath version = _super.version;

    @SuppressWarnings({"all", "rawtypes", "unchecked"})
    public QBaseSubject(String variable) {
        this((Class) BaseSubject.class, forVariable(variable), INITS);
    }

    @SuppressWarnings({"all", "rawtypes", "unchecked"})
    public QBaseSubject(Path<? extends BaseSubject> path) {
        this((Class) path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBaseSubject(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    @SuppressWarnings({"all", "rawtypes", "unchecked"})
    public QBaseSubject(PathMetadata metadata, PathInits inits) {
        this((Class) BaseSubject.class, metadata, inits);
    }

    public QBaseSubject(Class<? extends BaseSubject<? extends java.io.Serializable, ? extends io.github.edmaputra.ed.edbase.model.BaseAddress>> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.address = inits.isInitialized("address") ? new io.github.edmaputra.ed.edbase.model.QBaseAddress(forProperty("address")) : null;
    }

}

