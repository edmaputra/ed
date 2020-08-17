package io.github.edmaputra.ed.edbase.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QBaseAddress is a Querydsl query type for BaseAddress
 */
@Generated("com.querydsl.codegen.SupertypeSerializer")
public class QBaseAddress extends EntityPathBase<BaseAddress<? extends java.io.Serializable>> {

    private static final long serialVersionUID = -42448407L;

    public static final QBaseAddress baseAddress = new QBaseAddress("baseAddress");

    public final QBaseIdEntity _super = new QBaseIdEntity(this);

    public final StringPath city = createString("city");

    public final StringPath country = createString("country");

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

    //inherited
    public final StringPath note = _super.note;

    public final StringPath province = createString("province");

    public final StringPath street = createString("street");

    //inherited
    public final StringPath updater = _super.updater;

    //inherited
    public final DateTimePath<java.time.ZonedDateTime> updateTime = _super.updateTime;

    //inherited
    public final StringPath version = _super.version;

    public final StringPath zipCode = createString("zipCode");

    @SuppressWarnings({"all", "rawtypes", "unchecked"})
    public QBaseAddress(String variable) {
        super((Class) BaseAddress.class, forVariable(variable));
    }

    @SuppressWarnings({"all", "rawtypes", "unchecked"})
    public QBaseAddress(Path<? extends BaseAddress> path) {
        super((Class) path.getType(), path.getMetadata());
    }

    @SuppressWarnings({"all", "rawtypes", "unchecked"})
    public QBaseAddress(PathMetadata metadata) {
        super((Class) BaseAddress.class, metadata);
    }

}

