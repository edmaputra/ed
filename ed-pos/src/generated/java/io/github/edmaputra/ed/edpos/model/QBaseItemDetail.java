package io.github.edmaputra.ed.edpos.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBaseItemDetail is a Querydsl query type for BaseItemDetail
 */
@Generated("com.querydsl.codegen.SupertypeSerializer")
public class QBaseItemDetail extends EntityPathBase<BaseItemDetail<? extends BaseItem<?, ?>, ? extends java.io.Serializable>> {

    private static final long serialVersionUID = 958766364L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBaseItemDetail baseItemDetail = new QBaseItemDetail("baseItemDetail");

    public final io.github.edmaputra.ed.edbase.model.QBaseIdEntity _super = new io.github.edmaputra.ed.edbase.model.QBaseIdEntity(this);

    public final StringPath barcode = createString("barcode");

    public final NumberPath<java.math.BigDecimal> costPrice = createNumber("costPrice", java.math.BigDecimal.class);

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

    public final DateTimePath<java.time.ZonedDateTime> expiryDate = createDateTime("expiryDate", java.time.ZonedDateTime.class);

    //inherited
    public final SimplePath<java.io.Serializable> id = _super.id;

    public final QBaseItem item;

    //inherited
    public final StringPath note = _super.note;

    public final NumberPath<java.math.BigDecimal> sellPrice = createNumber("sellPrice", java.math.BigDecimal.class);

    public final NumberPath<Integer> stock = createNumber("stock", Integer.class);

    public final NumberPath<Integer> stockAlert = createNumber("stockAlert", Integer.class);

    public final StringPath stockKeepingUnit = createString("stockKeepingUnit");

    //inherited
    public final StringPath updater = _super.updater;

    //inherited
    public final DateTimePath<java.time.ZonedDateTime> updateTime = _super.updateTime;

    public final StringPath variant = createString("variant");

    //inherited
    public final StringPath version = _super.version;

    @SuppressWarnings({"all", "rawtypes", "unchecked"})
    public QBaseItemDetail(String variable) {
        this((Class) BaseItemDetail.class, forVariable(variable), INITS);
    }

    @SuppressWarnings({"all", "rawtypes", "unchecked"})
    public QBaseItemDetail(Path<? extends BaseItemDetail> path) {
        this((Class) path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBaseItemDetail(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    @SuppressWarnings({"all", "rawtypes", "unchecked"})
    public QBaseItemDetail(PathMetadata metadata, PathInits inits) {
        this((Class) BaseItemDetail.class, metadata, inits);
    }

    public QBaseItemDetail(Class<? extends BaseItemDetail<? extends BaseItem<?, ?>, ? extends java.io.Serializable>> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.item = inits.isInitialized("item") ? new QBaseItem(forProperty("item"), inits.get("item")) : null;
    }

}

