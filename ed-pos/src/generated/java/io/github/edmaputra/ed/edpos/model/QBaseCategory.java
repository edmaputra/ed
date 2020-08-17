package io.github.edmaputra.ed.edpos.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBaseCategory is a Querydsl query type for BaseCategory
 */
@Generated("com.querydsl.codegen.SupertypeSerializer")
public class QBaseCategory extends EntityPathBase<BaseCategory<? extends java.io.Serializable, ? extends BaseItem<?, ?>>> {

    private static final long serialVersionUID = -1947874602L;

    public static final QBaseCategory baseCategory = new QBaseCategory("baseCategory");

    public final io.github.edmaputra.ed.edbase.model.QBaseIdAndNameEntity _super = new io.github.edmaputra.ed.edbase.model.QBaseIdAndNameEntity(this);

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

    public final SetPath<BaseItem<?, ?>, QBaseItem> items = this.<BaseItem<?, ?>, QBaseItem>createSet("items", BaseItem.class, QBaseItem.class, PathInits.DIRECT2);

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
    public QBaseCategory(String variable) {
        super((Class) BaseCategory.class, forVariable(variable));
    }

    @SuppressWarnings({"all", "rawtypes", "unchecked"})
    public QBaseCategory(Path<? extends BaseCategory> path) {
        super((Class) path.getType(), path.getMetadata());
    }

    @SuppressWarnings({"all", "rawtypes", "unchecked"})
    public QBaseCategory(PathMetadata metadata) {
        super((Class) BaseCategory.class, metadata);
    }

}

