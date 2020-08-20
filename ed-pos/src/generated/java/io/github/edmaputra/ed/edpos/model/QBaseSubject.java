package io.github.edmaputra.ed.edpos.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QBaseSubject is a Querydsl query type for BaseSubject
 */
@Generated("com.querydsl.codegen.SupertypeSerializer")
public class QBaseSubject extends EntityPathBase<BaseSubject<? extends java.io.Serializable>> {

    private static final long serialVersionUID = 1254239316L;

    public static final QBaseSubject baseSubject = new QBaseSubject("baseSubject");

    public final io.github.edmaputra.ed.edbase.model.QBaseAddress _super = new io.github.edmaputra.ed.edbase.model.QBaseAddress(this);

    //inherited
    public final StringPath city = _super.city;

    //inherited
    public final StringPath country = _super.country;

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

    public final StringPath name = createString("name");

    //inherited
    public final StringPath note = _super.note;

    public final StringPath phone = createString("phone");

    //inherited
    public final StringPath province = _super.province;

    //inherited
    public final StringPath street = _super.street;

    //inherited
    public final StringPath updater = _super.updater;

    //inherited
    public final DateTimePath<java.time.ZonedDateTime> updateTime = _super.updateTime;

    //inherited
    public final StringPath version = _super.version;

    //inherited
    public final StringPath zipCode = _super.zipCode;

    @SuppressWarnings({"all", "rawtypes", "unchecked"})
    public QBaseSubject(String variable) {
        super((Class) BaseSubject.class, forVariable(variable));
    }

    @SuppressWarnings({"all", "rawtypes", "unchecked"})
    public QBaseSubject(Path<? extends BaseSubject> path) {
        super((Class) path.getType(), path.getMetadata());
    }

    @SuppressWarnings({"all", "rawtypes", "unchecked"})
    public QBaseSubject(PathMetadata metadata) {
        super((Class) BaseSubject.class, metadata);
    }

}

