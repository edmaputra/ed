package io.github.edmaputra.ed.edpos.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBaseSupplier is a Querydsl query type for BaseSupplier
 */
@Generated("com.querydsl.codegen.SupertypeSerializer")
public class QBaseSupplier extends EntityPathBase<BaseSupplier<? extends java.io.Serializable, ? extends io.github.edmaputra.ed.edbase.model.BaseAddress>> {

    private static final long serialVersionUID = 633276324L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBaseSupplier baseSupplier = new QBaseSupplier("baseSupplier");

    public final QBaseSubject _super;

    // inherited
    public final io.github.edmaputra.ed.edbase.model.QBaseAddress address;

    //inherited
    public final DateTimePath<java.time.ZonedDateTime> createTime;

    //inherited
    public final StringPath creator;

    //inherited
    public final StringPath deleteBy;

    //inherited
    public final BooleanPath deleteFlag;

    //inherited
    public final DateTimePath<java.time.ZonedDateTime> deleteTime;

    //inherited
    public final StringPath email;

    //inherited
    public final SimplePath<java.io.Serializable> id;

    //inherited
    public final StringPath name;

    //inherited
    public final StringPath note;

    //inherited
    public final StringPath phone;

    //inherited
    public final StringPath updater;

    //inherited
    public final DateTimePath<java.time.ZonedDateTime> updateTime;

    //inherited
    public final StringPath version;

    @SuppressWarnings({"all", "rawtypes", "unchecked"})
    public QBaseSupplier(String variable) {
        this((Class) BaseSupplier.class, forVariable(variable), INITS);
    }

    @SuppressWarnings({"all", "rawtypes", "unchecked"})
    public QBaseSupplier(Path<? extends BaseSupplier> path) {
        this((Class) path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBaseSupplier(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    @SuppressWarnings({"all", "rawtypes", "unchecked"})
    public QBaseSupplier(PathMetadata metadata, PathInits inits) {
        this((Class) BaseSupplier.class, metadata, inits);
    }

    public QBaseSupplier(Class<? extends BaseSupplier<? extends java.io.Serializable, ? extends io.github.edmaputra.ed.edbase.model.BaseAddress>> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new QBaseSubject(type, metadata, inits);
        this.address = _super.address;
        this.createTime = _super.createTime;
        this.creator = _super.creator;
        this.deleteBy = _super.deleteBy;
        this.deleteFlag = _super.deleteFlag;
        this.deleteTime = _super.deleteTime;
        this.email = _super.email;
        this.id = _super.id;
        this.name = _super.name;
        this.note = _super.note;
        this.phone = _super.phone;
        this.updater = _super.updater;
        this.updateTime = _super.updateTime;
        this.version = _super.version;
    }

}

