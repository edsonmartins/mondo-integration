/**
 * Autogenerated by Thrift Compiler (0.9.3)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package uk.ac.york.mondo.integration.api;

import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;

import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.EncodingUtils;
import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.server.AbstractNonblockingServer.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.EnumMap;
import java.util.Set;
import java.util.HashSet;
import java.util.EnumSet;
import java.util.Collections;
import java.util.BitSet;
import java.nio.ByteBuffer;
import java.util.Arrays;
import javax.annotation.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked"})
public class HawkChangeEvent extends org.apache.thrift.TUnion<HawkChangeEvent, HawkChangeEvent._Fields> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("HawkChangeEvent");
  private static final org.apache.thrift.protocol.TField MODEL_ELEMENT_ADDITION_FIELD_DESC = new org.apache.thrift.protocol.TField("modelElementAddition", org.apache.thrift.protocol.TType.STRUCT, (short)1);
  private static final org.apache.thrift.protocol.TField MODEL_ELEMENT_REMOVAL_FIELD_DESC = new org.apache.thrift.protocol.TField("modelElementRemoval", org.apache.thrift.protocol.TType.STRUCT, (short)2);
  private static final org.apache.thrift.protocol.TField MODEL_ELEMENT_ATTRIBUTE_UPDATE_FIELD_DESC = new org.apache.thrift.protocol.TField("modelElementAttributeUpdate", org.apache.thrift.protocol.TType.STRUCT, (short)3);
  private static final org.apache.thrift.protocol.TField MODEL_ELEMENT_ATTRIBUTE_REMOVAL_FIELD_DESC = new org.apache.thrift.protocol.TField("modelElementAttributeRemoval", org.apache.thrift.protocol.TType.STRUCT, (short)4);
  private static final org.apache.thrift.protocol.TField REFERENCE_ADDITION_FIELD_DESC = new org.apache.thrift.protocol.TField("referenceAddition", org.apache.thrift.protocol.TType.STRUCT, (short)5);
  private static final org.apache.thrift.protocol.TField REFERENCE_REMOVAL_FIELD_DESC = new org.apache.thrift.protocol.TField("referenceRemoval", org.apache.thrift.protocol.TType.STRUCT, (short)6);
  private static final org.apache.thrift.protocol.TField SYNC_START_FIELD_DESC = new org.apache.thrift.protocol.TField("syncStart", org.apache.thrift.protocol.TType.STRUCT, (short)7);
  private static final org.apache.thrift.protocol.TField SYNC_END_FIELD_DESC = new org.apache.thrift.protocol.TField("syncEnd", org.apache.thrift.protocol.TType.STRUCT, (short)8);
  private static final org.apache.thrift.protocol.TField FILE_ADDITION_FIELD_DESC = new org.apache.thrift.protocol.TField("fileAddition", org.apache.thrift.protocol.TType.STRUCT, (short)9);
  private static final org.apache.thrift.protocol.TField FILE_REMOVAL_FIELD_DESC = new org.apache.thrift.protocol.TField("fileRemoval", org.apache.thrift.protocol.TType.STRUCT, (short)10);

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    MODEL_ELEMENT_ADDITION((short)1, "modelElementAddition"),
    MODEL_ELEMENT_REMOVAL((short)2, "modelElementRemoval"),
    MODEL_ELEMENT_ATTRIBUTE_UPDATE((short)3, "modelElementAttributeUpdate"),
    MODEL_ELEMENT_ATTRIBUTE_REMOVAL((short)4, "modelElementAttributeRemoval"),
    REFERENCE_ADDITION((short)5, "referenceAddition"),
    REFERENCE_REMOVAL((short)6, "referenceRemoval"),
    SYNC_START((short)7, "syncStart"),
    SYNC_END((short)8, "syncEnd"),
    FILE_ADDITION((short)9, "fileAddition"),
    FILE_REMOVAL((short)10, "fileRemoval");

    private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

    static {
      for (_Fields field : EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // MODEL_ELEMENT_ADDITION
          return MODEL_ELEMENT_ADDITION;
        case 2: // MODEL_ELEMENT_REMOVAL
          return MODEL_ELEMENT_REMOVAL;
        case 3: // MODEL_ELEMENT_ATTRIBUTE_UPDATE
          return MODEL_ELEMENT_ATTRIBUTE_UPDATE;
        case 4: // MODEL_ELEMENT_ATTRIBUTE_REMOVAL
          return MODEL_ELEMENT_ATTRIBUTE_REMOVAL;
        case 5: // REFERENCE_ADDITION
          return REFERENCE_ADDITION;
        case 6: // REFERENCE_REMOVAL
          return REFERENCE_REMOVAL;
        case 7: // SYNC_START
          return SYNC_START;
        case 8: // SYNC_END
          return SYNC_END;
        case 9: // FILE_ADDITION
          return FILE_ADDITION;
        case 10: // FILE_REMOVAL
          return FILE_REMOVAL;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final String _fieldName;

    _Fields(short thriftId, String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public String getFieldName() {
      return _fieldName;
    }
  }

  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.MODEL_ELEMENT_ADDITION, new org.apache.thrift.meta_data.FieldMetaData("modelElementAddition", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, HawkModelElementAdditionEvent.class)));
    tmpMap.put(_Fields.MODEL_ELEMENT_REMOVAL, new org.apache.thrift.meta_data.FieldMetaData("modelElementRemoval", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, HawkModelElementRemovalEvent.class)));
    tmpMap.put(_Fields.MODEL_ELEMENT_ATTRIBUTE_UPDATE, new org.apache.thrift.meta_data.FieldMetaData("modelElementAttributeUpdate", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, HawkAttributeUpdateEvent.class)));
    tmpMap.put(_Fields.MODEL_ELEMENT_ATTRIBUTE_REMOVAL, new org.apache.thrift.meta_data.FieldMetaData("modelElementAttributeRemoval", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, HawkAttributeRemovalEvent.class)));
    tmpMap.put(_Fields.REFERENCE_ADDITION, new org.apache.thrift.meta_data.FieldMetaData("referenceAddition", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, HawkReferenceAdditionEvent.class)));
    tmpMap.put(_Fields.REFERENCE_REMOVAL, new org.apache.thrift.meta_data.FieldMetaData("referenceRemoval", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, HawkReferenceRemovalEvent.class)));
    tmpMap.put(_Fields.SYNC_START, new org.apache.thrift.meta_data.FieldMetaData("syncStart", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, HawkSynchronizationStartEvent.class)));
    tmpMap.put(_Fields.SYNC_END, new org.apache.thrift.meta_data.FieldMetaData("syncEnd", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, HawkSynchronizationEndEvent.class)));
    tmpMap.put(_Fields.FILE_ADDITION, new org.apache.thrift.meta_data.FieldMetaData("fileAddition", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, HawkFileAdditionEvent.class)));
    tmpMap.put(_Fields.FILE_REMOVAL, new org.apache.thrift.meta_data.FieldMetaData("fileRemoval", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, HawkFileRemovalEvent.class)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(HawkChangeEvent.class, metaDataMap);
  }

  public HawkChangeEvent() {
    super();
  }

  public HawkChangeEvent(_Fields setField, Object value) {
    super(setField, value);
  }

  public HawkChangeEvent(HawkChangeEvent other) {
    super(other);
  }
  public HawkChangeEvent deepCopy() {
    return new HawkChangeEvent(this);
  }

  public static HawkChangeEvent modelElementAddition(HawkModelElementAdditionEvent value) {
    HawkChangeEvent x = new HawkChangeEvent();
    x.setModelElementAddition(value);
    return x;
  }

  public static HawkChangeEvent modelElementRemoval(HawkModelElementRemovalEvent value) {
    HawkChangeEvent x = new HawkChangeEvent();
    x.setModelElementRemoval(value);
    return x;
  }

  public static HawkChangeEvent modelElementAttributeUpdate(HawkAttributeUpdateEvent value) {
    HawkChangeEvent x = new HawkChangeEvent();
    x.setModelElementAttributeUpdate(value);
    return x;
  }

  public static HawkChangeEvent modelElementAttributeRemoval(HawkAttributeRemovalEvent value) {
    HawkChangeEvent x = new HawkChangeEvent();
    x.setModelElementAttributeRemoval(value);
    return x;
  }

  public static HawkChangeEvent referenceAddition(HawkReferenceAdditionEvent value) {
    HawkChangeEvent x = new HawkChangeEvent();
    x.setReferenceAddition(value);
    return x;
  }

  public static HawkChangeEvent referenceRemoval(HawkReferenceRemovalEvent value) {
    HawkChangeEvent x = new HawkChangeEvent();
    x.setReferenceRemoval(value);
    return x;
  }

  public static HawkChangeEvent syncStart(HawkSynchronizationStartEvent value) {
    HawkChangeEvent x = new HawkChangeEvent();
    x.setSyncStart(value);
    return x;
  }

  public static HawkChangeEvent syncEnd(HawkSynchronizationEndEvent value) {
    HawkChangeEvent x = new HawkChangeEvent();
    x.setSyncEnd(value);
    return x;
  }

  public static HawkChangeEvent fileAddition(HawkFileAdditionEvent value) {
    HawkChangeEvent x = new HawkChangeEvent();
    x.setFileAddition(value);
    return x;
  }

  public static HawkChangeEvent fileRemoval(HawkFileRemovalEvent value) {
    HawkChangeEvent x = new HawkChangeEvent();
    x.setFileRemoval(value);
    return x;
  }


  @Override
  protected void checkType(_Fields setField, Object value) throws ClassCastException {
    switch (setField) {
      case MODEL_ELEMENT_ADDITION:
        if (value instanceof HawkModelElementAdditionEvent) {
          break;
        }
        throw new ClassCastException("Was expecting value of type HawkModelElementAdditionEvent for field 'modelElementAddition', but got " + value.getClass().getSimpleName());
      case MODEL_ELEMENT_REMOVAL:
        if (value instanceof HawkModelElementRemovalEvent) {
          break;
        }
        throw new ClassCastException("Was expecting value of type HawkModelElementRemovalEvent for field 'modelElementRemoval', but got " + value.getClass().getSimpleName());
      case MODEL_ELEMENT_ATTRIBUTE_UPDATE:
        if (value instanceof HawkAttributeUpdateEvent) {
          break;
        }
        throw new ClassCastException("Was expecting value of type HawkAttributeUpdateEvent for field 'modelElementAttributeUpdate', but got " + value.getClass().getSimpleName());
      case MODEL_ELEMENT_ATTRIBUTE_REMOVAL:
        if (value instanceof HawkAttributeRemovalEvent) {
          break;
        }
        throw new ClassCastException("Was expecting value of type HawkAttributeRemovalEvent for field 'modelElementAttributeRemoval', but got " + value.getClass().getSimpleName());
      case REFERENCE_ADDITION:
        if (value instanceof HawkReferenceAdditionEvent) {
          break;
        }
        throw new ClassCastException("Was expecting value of type HawkReferenceAdditionEvent for field 'referenceAddition', but got " + value.getClass().getSimpleName());
      case REFERENCE_REMOVAL:
        if (value instanceof HawkReferenceRemovalEvent) {
          break;
        }
        throw new ClassCastException("Was expecting value of type HawkReferenceRemovalEvent for field 'referenceRemoval', but got " + value.getClass().getSimpleName());
      case SYNC_START:
        if (value instanceof HawkSynchronizationStartEvent) {
          break;
        }
        throw new ClassCastException("Was expecting value of type HawkSynchronizationStartEvent for field 'syncStart', but got " + value.getClass().getSimpleName());
      case SYNC_END:
        if (value instanceof HawkSynchronizationEndEvent) {
          break;
        }
        throw new ClassCastException("Was expecting value of type HawkSynchronizationEndEvent for field 'syncEnd', but got " + value.getClass().getSimpleName());
      case FILE_ADDITION:
        if (value instanceof HawkFileAdditionEvent) {
          break;
        }
        throw new ClassCastException("Was expecting value of type HawkFileAdditionEvent for field 'fileAddition', but got " + value.getClass().getSimpleName());
      case FILE_REMOVAL:
        if (value instanceof HawkFileRemovalEvent) {
          break;
        }
        throw new ClassCastException("Was expecting value of type HawkFileRemovalEvent for field 'fileRemoval', but got " + value.getClass().getSimpleName());
      default:
        throw new IllegalArgumentException("Unknown field id " + setField);
    }
  }

  @Override
  protected Object standardSchemeReadValue(org.apache.thrift.protocol.TProtocol iprot, org.apache.thrift.protocol.TField field) throws org.apache.thrift.TException {
    _Fields setField = _Fields.findByThriftId(field.id);
    if (setField != null) {
      switch (setField) {
        case MODEL_ELEMENT_ADDITION:
          if (field.type == MODEL_ELEMENT_ADDITION_FIELD_DESC.type) {
            HawkModelElementAdditionEvent modelElementAddition;
            modelElementAddition = new HawkModelElementAdditionEvent();
            modelElementAddition.read(iprot);
            return modelElementAddition;
          } else {
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, field.type);
            return null;
          }
        case MODEL_ELEMENT_REMOVAL:
          if (field.type == MODEL_ELEMENT_REMOVAL_FIELD_DESC.type) {
            HawkModelElementRemovalEvent modelElementRemoval;
            modelElementRemoval = new HawkModelElementRemovalEvent();
            modelElementRemoval.read(iprot);
            return modelElementRemoval;
          } else {
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, field.type);
            return null;
          }
        case MODEL_ELEMENT_ATTRIBUTE_UPDATE:
          if (field.type == MODEL_ELEMENT_ATTRIBUTE_UPDATE_FIELD_DESC.type) {
            HawkAttributeUpdateEvent modelElementAttributeUpdate;
            modelElementAttributeUpdate = new HawkAttributeUpdateEvent();
            modelElementAttributeUpdate.read(iprot);
            return modelElementAttributeUpdate;
          } else {
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, field.type);
            return null;
          }
        case MODEL_ELEMENT_ATTRIBUTE_REMOVAL:
          if (field.type == MODEL_ELEMENT_ATTRIBUTE_REMOVAL_FIELD_DESC.type) {
            HawkAttributeRemovalEvent modelElementAttributeRemoval;
            modelElementAttributeRemoval = new HawkAttributeRemovalEvent();
            modelElementAttributeRemoval.read(iprot);
            return modelElementAttributeRemoval;
          } else {
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, field.type);
            return null;
          }
        case REFERENCE_ADDITION:
          if (field.type == REFERENCE_ADDITION_FIELD_DESC.type) {
            HawkReferenceAdditionEvent referenceAddition;
            referenceAddition = new HawkReferenceAdditionEvent();
            referenceAddition.read(iprot);
            return referenceAddition;
          } else {
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, field.type);
            return null;
          }
        case REFERENCE_REMOVAL:
          if (field.type == REFERENCE_REMOVAL_FIELD_DESC.type) {
            HawkReferenceRemovalEvent referenceRemoval;
            referenceRemoval = new HawkReferenceRemovalEvent();
            referenceRemoval.read(iprot);
            return referenceRemoval;
          } else {
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, field.type);
            return null;
          }
        case SYNC_START:
          if (field.type == SYNC_START_FIELD_DESC.type) {
            HawkSynchronizationStartEvent syncStart;
            syncStart = new HawkSynchronizationStartEvent();
            syncStart.read(iprot);
            return syncStart;
          } else {
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, field.type);
            return null;
          }
        case SYNC_END:
          if (field.type == SYNC_END_FIELD_DESC.type) {
            HawkSynchronizationEndEvent syncEnd;
            syncEnd = new HawkSynchronizationEndEvent();
            syncEnd.read(iprot);
            return syncEnd;
          } else {
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, field.type);
            return null;
          }
        case FILE_ADDITION:
          if (field.type == FILE_ADDITION_FIELD_DESC.type) {
            HawkFileAdditionEvent fileAddition;
            fileAddition = new HawkFileAdditionEvent();
            fileAddition.read(iprot);
            return fileAddition;
          } else {
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, field.type);
            return null;
          }
        case FILE_REMOVAL:
          if (field.type == FILE_REMOVAL_FIELD_DESC.type) {
            HawkFileRemovalEvent fileRemoval;
            fileRemoval = new HawkFileRemovalEvent();
            fileRemoval.read(iprot);
            return fileRemoval;
          } else {
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, field.type);
            return null;
          }
        default:
          throw new IllegalStateException("setField wasn't null, but didn't match any of the case statements!");
      }
    } else {
      org.apache.thrift.protocol.TProtocolUtil.skip(iprot, field.type);
      return null;
    }
  }

  @Override
  protected void standardSchemeWriteValue(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    switch (setField_) {
      case MODEL_ELEMENT_ADDITION:
        HawkModelElementAdditionEvent modelElementAddition = (HawkModelElementAdditionEvent)value_;
        modelElementAddition.write(oprot);
        return;
      case MODEL_ELEMENT_REMOVAL:
        HawkModelElementRemovalEvent modelElementRemoval = (HawkModelElementRemovalEvent)value_;
        modelElementRemoval.write(oprot);
        return;
      case MODEL_ELEMENT_ATTRIBUTE_UPDATE:
        HawkAttributeUpdateEvent modelElementAttributeUpdate = (HawkAttributeUpdateEvent)value_;
        modelElementAttributeUpdate.write(oprot);
        return;
      case MODEL_ELEMENT_ATTRIBUTE_REMOVAL:
        HawkAttributeRemovalEvent modelElementAttributeRemoval = (HawkAttributeRemovalEvent)value_;
        modelElementAttributeRemoval.write(oprot);
        return;
      case REFERENCE_ADDITION:
        HawkReferenceAdditionEvent referenceAddition = (HawkReferenceAdditionEvent)value_;
        referenceAddition.write(oprot);
        return;
      case REFERENCE_REMOVAL:
        HawkReferenceRemovalEvent referenceRemoval = (HawkReferenceRemovalEvent)value_;
        referenceRemoval.write(oprot);
        return;
      case SYNC_START:
        HawkSynchronizationStartEvent syncStart = (HawkSynchronizationStartEvent)value_;
        syncStart.write(oprot);
        return;
      case SYNC_END:
        HawkSynchronizationEndEvent syncEnd = (HawkSynchronizationEndEvent)value_;
        syncEnd.write(oprot);
        return;
      case FILE_ADDITION:
        HawkFileAdditionEvent fileAddition = (HawkFileAdditionEvent)value_;
        fileAddition.write(oprot);
        return;
      case FILE_REMOVAL:
        HawkFileRemovalEvent fileRemoval = (HawkFileRemovalEvent)value_;
        fileRemoval.write(oprot);
        return;
      default:
        throw new IllegalStateException("Cannot write union with unknown field " + setField_);
    }
  }

  @Override
  protected Object tupleSchemeReadValue(org.apache.thrift.protocol.TProtocol iprot, short fieldID) throws org.apache.thrift.TException {
    _Fields setField = _Fields.findByThriftId(fieldID);
    if (setField != null) {
      switch (setField) {
        case MODEL_ELEMENT_ADDITION:
          HawkModelElementAdditionEvent modelElementAddition;
          modelElementAddition = new HawkModelElementAdditionEvent();
          modelElementAddition.read(iprot);
          return modelElementAddition;
        case MODEL_ELEMENT_REMOVAL:
          HawkModelElementRemovalEvent modelElementRemoval;
          modelElementRemoval = new HawkModelElementRemovalEvent();
          modelElementRemoval.read(iprot);
          return modelElementRemoval;
        case MODEL_ELEMENT_ATTRIBUTE_UPDATE:
          HawkAttributeUpdateEvent modelElementAttributeUpdate;
          modelElementAttributeUpdate = new HawkAttributeUpdateEvent();
          modelElementAttributeUpdate.read(iprot);
          return modelElementAttributeUpdate;
        case MODEL_ELEMENT_ATTRIBUTE_REMOVAL:
          HawkAttributeRemovalEvent modelElementAttributeRemoval;
          modelElementAttributeRemoval = new HawkAttributeRemovalEvent();
          modelElementAttributeRemoval.read(iprot);
          return modelElementAttributeRemoval;
        case REFERENCE_ADDITION:
          HawkReferenceAdditionEvent referenceAddition;
          referenceAddition = new HawkReferenceAdditionEvent();
          referenceAddition.read(iprot);
          return referenceAddition;
        case REFERENCE_REMOVAL:
          HawkReferenceRemovalEvent referenceRemoval;
          referenceRemoval = new HawkReferenceRemovalEvent();
          referenceRemoval.read(iprot);
          return referenceRemoval;
        case SYNC_START:
          HawkSynchronizationStartEvent syncStart;
          syncStart = new HawkSynchronizationStartEvent();
          syncStart.read(iprot);
          return syncStart;
        case SYNC_END:
          HawkSynchronizationEndEvent syncEnd;
          syncEnd = new HawkSynchronizationEndEvent();
          syncEnd.read(iprot);
          return syncEnd;
        case FILE_ADDITION:
          HawkFileAdditionEvent fileAddition;
          fileAddition = new HawkFileAdditionEvent();
          fileAddition.read(iprot);
          return fileAddition;
        case FILE_REMOVAL:
          HawkFileRemovalEvent fileRemoval;
          fileRemoval = new HawkFileRemovalEvent();
          fileRemoval.read(iprot);
          return fileRemoval;
        default:
          throw new IllegalStateException("setField wasn't null, but didn't match any of the case statements!");
      }
    } else {
      throw new TProtocolException("Couldn't find a field with field id " + fieldID);
    }
  }

  @Override
  protected void tupleSchemeWriteValue(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    switch (setField_) {
      case MODEL_ELEMENT_ADDITION:
        HawkModelElementAdditionEvent modelElementAddition = (HawkModelElementAdditionEvent)value_;
        modelElementAddition.write(oprot);
        return;
      case MODEL_ELEMENT_REMOVAL:
        HawkModelElementRemovalEvent modelElementRemoval = (HawkModelElementRemovalEvent)value_;
        modelElementRemoval.write(oprot);
        return;
      case MODEL_ELEMENT_ATTRIBUTE_UPDATE:
        HawkAttributeUpdateEvent modelElementAttributeUpdate = (HawkAttributeUpdateEvent)value_;
        modelElementAttributeUpdate.write(oprot);
        return;
      case MODEL_ELEMENT_ATTRIBUTE_REMOVAL:
        HawkAttributeRemovalEvent modelElementAttributeRemoval = (HawkAttributeRemovalEvent)value_;
        modelElementAttributeRemoval.write(oprot);
        return;
      case REFERENCE_ADDITION:
        HawkReferenceAdditionEvent referenceAddition = (HawkReferenceAdditionEvent)value_;
        referenceAddition.write(oprot);
        return;
      case REFERENCE_REMOVAL:
        HawkReferenceRemovalEvent referenceRemoval = (HawkReferenceRemovalEvent)value_;
        referenceRemoval.write(oprot);
        return;
      case SYNC_START:
        HawkSynchronizationStartEvent syncStart = (HawkSynchronizationStartEvent)value_;
        syncStart.write(oprot);
        return;
      case SYNC_END:
        HawkSynchronizationEndEvent syncEnd = (HawkSynchronizationEndEvent)value_;
        syncEnd.write(oprot);
        return;
      case FILE_ADDITION:
        HawkFileAdditionEvent fileAddition = (HawkFileAdditionEvent)value_;
        fileAddition.write(oprot);
        return;
      case FILE_REMOVAL:
        HawkFileRemovalEvent fileRemoval = (HawkFileRemovalEvent)value_;
        fileRemoval.write(oprot);
        return;
      default:
        throw new IllegalStateException("Cannot write union with unknown field " + setField_);
    }
  }

  @Override
  protected org.apache.thrift.protocol.TField getFieldDesc(_Fields setField) {
    switch (setField) {
      case MODEL_ELEMENT_ADDITION:
        return MODEL_ELEMENT_ADDITION_FIELD_DESC;
      case MODEL_ELEMENT_REMOVAL:
        return MODEL_ELEMENT_REMOVAL_FIELD_DESC;
      case MODEL_ELEMENT_ATTRIBUTE_UPDATE:
        return MODEL_ELEMENT_ATTRIBUTE_UPDATE_FIELD_DESC;
      case MODEL_ELEMENT_ATTRIBUTE_REMOVAL:
        return MODEL_ELEMENT_ATTRIBUTE_REMOVAL_FIELD_DESC;
      case REFERENCE_ADDITION:
        return REFERENCE_ADDITION_FIELD_DESC;
      case REFERENCE_REMOVAL:
        return REFERENCE_REMOVAL_FIELD_DESC;
      case SYNC_START:
        return SYNC_START_FIELD_DESC;
      case SYNC_END:
        return SYNC_END_FIELD_DESC;
      case FILE_ADDITION:
        return FILE_ADDITION_FIELD_DESC;
      case FILE_REMOVAL:
        return FILE_REMOVAL_FIELD_DESC;
      default:
        throw new IllegalArgumentException("Unknown field id " + setField);
    }
  }

  @Override
  protected org.apache.thrift.protocol.TStruct getStructDesc() {
    return STRUCT_DESC;
  }

  @Override
  protected _Fields enumForId(short id) {
    return _Fields.findByThriftIdOrThrow(id);
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }


  public HawkModelElementAdditionEvent getModelElementAddition() {
    if (getSetField() == _Fields.MODEL_ELEMENT_ADDITION) {
      return (HawkModelElementAdditionEvent)getFieldValue();
    } else {
      throw new RuntimeException("Cannot get field 'modelElementAddition' because union is currently set to " + getFieldDesc(getSetField()).name);
    }
  }

  public void setModelElementAddition(HawkModelElementAdditionEvent value) {
    if (value == null) throw new NullPointerException();
    setField_ = _Fields.MODEL_ELEMENT_ADDITION;
    value_ = value;
  }

  public HawkModelElementRemovalEvent getModelElementRemoval() {
    if (getSetField() == _Fields.MODEL_ELEMENT_REMOVAL) {
      return (HawkModelElementRemovalEvent)getFieldValue();
    } else {
      throw new RuntimeException("Cannot get field 'modelElementRemoval' because union is currently set to " + getFieldDesc(getSetField()).name);
    }
  }

  public void setModelElementRemoval(HawkModelElementRemovalEvent value) {
    if (value == null) throw new NullPointerException();
    setField_ = _Fields.MODEL_ELEMENT_REMOVAL;
    value_ = value;
  }

  public HawkAttributeUpdateEvent getModelElementAttributeUpdate() {
    if (getSetField() == _Fields.MODEL_ELEMENT_ATTRIBUTE_UPDATE) {
      return (HawkAttributeUpdateEvent)getFieldValue();
    } else {
      throw new RuntimeException("Cannot get field 'modelElementAttributeUpdate' because union is currently set to " + getFieldDesc(getSetField()).name);
    }
  }

  public void setModelElementAttributeUpdate(HawkAttributeUpdateEvent value) {
    if (value == null) throw new NullPointerException();
    setField_ = _Fields.MODEL_ELEMENT_ATTRIBUTE_UPDATE;
    value_ = value;
  }

  public HawkAttributeRemovalEvent getModelElementAttributeRemoval() {
    if (getSetField() == _Fields.MODEL_ELEMENT_ATTRIBUTE_REMOVAL) {
      return (HawkAttributeRemovalEvent)getFieldValue();
    } else {
      throw new RuntimeException("Cannot get field 'modelElementAttributeRemoval' because union is currently set to " + getFieldDesc(getSetField()).name);
    }
  }

  public void setModelElementAttributeRemoval(HawkAttributeRemovalEvent value) {
    if (value == null) throw new NullPointerException();
    setField_ = _Fields.MODEL_ELEMENT_ATTRIBUTE_REMOVAL;
    value_ = value;
  }

  public HawkReferenceAdditionEvent getReferenceAddition() {
    if (getSetField() == _Fields.REFERENCE_ADDITION) {
      return (HawkReferenceAdditionEvent)getFieldValue();
    } else {
      throw new RuntimeException("Cannot get field 'referenceAddition' because union is currently set to " + getFieldDesc(getSetField()).name);
    }
  }

  public void setReferenceAddition(HawkReferenceAdditionEvent value) {
    if (value == null) throw new NullPointerException();
    setField_ = _Fields.REFERENCE_ADDITION;
    value_ = value;
  }

  public HawkReferenceRemovalEvent getReferenceRemoval() {
    if (getSetField() == _Fields.REFERENCE_REMOVAL) {
      return (HawkReferenceRemovalEvent)getFieldValue();
    } else {
      throw new RuntimeException("Cannot get field 'referenceRemoval' because union is currently set to " + getFieldDesc(getSetField()).name);
    }
  }

  public void setReferenceRemoval(HawkReferenceRemovalEvent value) {
    if (value == null) throw new NullPointerException();
    setField_ = _Fields.REFERENCE_REMOVAL;
    value_ = value;
  }

  public HawkSynchronizationStartEvent getSyncStart() {
    if (getSetField() == _Fields.SYNC_START) {
      return (HawkSynchronizationStartEvent)getFieldValue();
    } else {
      throw new RuntimeException("Cannot get field 'syncStart' because union is currently set to " + getFieldDesc(getSetField()).name);
    }
  }

  public void setSyncStart(HawkSynchronizationStartEvent value) {
    if (value == null) throw new NullPointerException();
    setField_ = _Fields.SYNC_START;
    value_ = value;
  }

  public HawkSynchronizationEndEvent getSyncEnd() {
    if (getSetField() == _Fields.SYNC_END) {
      return (HawkSynchronizationEndEvent)getFieldValue();
    } else {
      throw new RuntimeException("Cannot get field 'syncEnd' because union is currently set to " + getFieldDesc(getSetField()).name);
    }
  }

  public void setSyncEnd(HawkSynchronizationEndEvent value) {
    if (value == null) throw new NullPointerException();
    setField_ = _Fields.SYNC_END;
    value_ = value;
  }

  public HawkFileAdditionEvent getFileAddition() {
    if (getSetField() == _Fields.FILE_ADDITION) {
      return (HawkFileAdditionEvent)getFieldValue();
    } else {
      throw new RuntimeException("Cannot get field 'fileAddition' because union is currently set to " + getFieldDesc(getSetField()).name);
    }
  }

  public void setFileAddition(HawkFileAdditionEvent value) {
    if (value == null) throw new NullPointerException();
    setField_ = _Fields.FILE_ADDITION;
    value_ = value;
  }

  public HawkFileRemovalEvent getFileRemoval() {
    if (getSetField() == _Fields.FILE_REMOVAL) {
      return (HawkFileRemovalEvent)getFieldValue();
    } else {
      throw new RuntimeException("Cannot get field 'fileRemoval' because union is currently set to " + getFieldDesc(getSetField()).name);
    }
  }

  public void setFileRemoval(HawkFileRemovalEvent value) {
    if (value == null) throw new NullPointerException();
    setField_ = _Fields.FILE_REMOVAL;
    value_ = value;
  }

  public boolean isSetModelElementAddition() {
    return setField_ == _Fields.MODEL_ELEMENT_ADDITION;
  }


  public boolean isSetModelElementRemoval() {
    return setField_ == _Fields.MODEL_ELEMENT_REMOVAL;
  }


  public boolean isSetModelElementAttributeUpdate() {
    return setField_ == _Fields.MODEL_ELEMENT_ATTRIBUTE_UPDATE;
  }


  public boolean isSetModelElementAttributeRemoval() {
    return setField_ == _Fields.MODEL_ELEMENT_ATTRIBUTE_REMOVAL;
  }


  public boolean isSetReferenceAddition() {
    return setField_ == _Fields.REFERENCE_ADDITION;
  }


  public boolean isSetReferenceRemoval() {
    return setField_ == _Fields.REFERENCE_REMOVAL;
  }


  public boolean isSetSyncStart() {
    return setField_ == _Fields.SYNC_START;
  }


  public boolean isSetSyncEnd() {
    return setField_ == _Fields.SYNC_END;
  }


  public boolean isSetFileAddition() {
    return setField_ == _Fields.FILE_ADDITION;
  }


  public boolean isSetFileRemoval() {
    return setField_ == _Fields.FILE_REMOVAL;
  }


  public boolean equals(Object other) {
    if (other instanceof HawkChangeEvent) {
      return equals((HawkChangeEvent)other);
    } else {
      return false;
    }
  }

  public boolean equals(HawkChangeEvent other) {
    return other != null && getSetField() == other.getSetField() && getFieldValue().equals(other.getFieldValue());
  }

  @Override
  public int compareTo(HawkChangeEvent other) {
    int lastComparison = org.apache.thrift.TBaseHelper.compareTo(getSetField(), other.getSetField());
    if (lastComparison == 0) {
      return org.apache.thrift.TBaseHelper.compareTo(getFieldValue(), other.getFieldValue());
    }
    return lastComparison;
  }


  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();
    list.add(this.getClass().getName());
    org.apache.thrift.TFieldIdEnum setField = getSetField();
    if (setField != null) {
      list.add(setField.getThriftFieldId());
      Object value = getFieldValue();
      if (value instanceof org.apache.thrift.TEnum) {
        list.add(((org.apache.thrift.TEnum)getFieldValue()).getValue());
      } else {
        list.add(value);
      }
    }
    return list.hashCode();
  }
  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }


  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
    try {
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }


}
