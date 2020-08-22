package io.github.edmaputra.ed.edpos.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBaseItem is a Querydsl query type for BaseItem
 */
@Generated("com.querydsl.codegen.SupertypeSerializer")
public class QBaseItem extends EntityPathBase<BaseItem<? extends BaseCategory<?>, ? extends java.io.Serializable>> {

    private static final long serialVersionUID = -944425749L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBaseItem baseItem = new QBaseItem("baseItem");

    public final io.github.edmaputra.ed.edbase.model.QBaseIdAndNameEntity _super = new io.github.edmaputra.ed.edbase.model.QBaseIdAndNameEntity(this);

    public final QBaseCategory category;

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
    public final StringPath name = _super.name;

    //inherited
    public final StringPath note = _super.note;

    //inherited
    public final StringPath updater = _super.updater;

    //inherited
    public final DateTimePath<java.time.ZonedDateTime> updateTime = _super.updateTime;

    //inherited
    public final StringPath version = _super.version;

    @SuppressWarnings({"all", "rawtypes", "unchecked"})
    public QBaseItem(String variable) {
        this((Class) BaseItem.class, forVariable(variable), INITS);
    }

    @SuppressWarnings({"all", "rawtypes", "unchecked"})
    public QBaseItem(Path<? extends BaseItem> path) {
        this((Class) path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBaseItem(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    @SuppressWarnings({"all", "rawtypes", "unchecked"})
    public QBaseItem(PathMetadata metadata, PathInits inits) {
        this((Class) BaseItem.class, metadata, inits);
    }

    public QBaseItem(Class<? extends BaseItem<? extends BaseCategory<?>, ? extends java.io.Serializable>> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.category = inits.isInitialized("category") ? new QBaseCategory(forProperty("category")) : null;
    }

}

