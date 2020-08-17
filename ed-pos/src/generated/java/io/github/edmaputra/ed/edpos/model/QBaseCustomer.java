package io.github.edmaputra.ed.edpos.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBaseCustomer is a Querydsl query type for BaseCustomer
 */
@Generated("com.querydsl.codegen.SupertypeSerializer")
public class QBaseCustomer extends EntityPathBase<BaseCustomer<? extends java.io.Serializable, ? extends io.github.edmaputra.ed.edbase.model.BaseAddress>> {

    private static final long serialVersionUID = -1392210506L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBaseCustomer baseCustomer = new QBaseCustomer("baseCustomer");

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
    public QBaseCustomer(String variable) {
        this((Class) BaseCustomer.class, forVariable(variable), INITS);
    }

    @SuppressWarnings({"all", "rawtypes", "unchecked"})
    public QBaseCustomer(Path<? extends BaseCustomer> path) {
        this((Class) path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBaseCustomer(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    @SuppressWarnings({"all", "rawtypes", "unchecked"})
    public QBaseCustomer(PathMetadata metadata, PathInits inits) {
        this((Class) BaseCustomer.class, metadata, inits);
    }

    public QBaseCustomer(Class<? extends BaseCustomer<? extends java.io.Serializable, ? extends io.github.edmaputra.ed.edbase.model.BaseAddress>> type, PathMetadata metadata, PathInits inits) {
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

