/**
 * Autogenerated by Thrift Compiler (0.9.2)
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
@Generated(value = "Autogenerated by Thrift Compiler (0.9.2)", date = "2015-7-20")
public class ReferenceSlot implements org.apache.thrift.TBase<ReferenceSlot, ReferenceSlot._Fields>, java.io.Serializable, Cloneable, Comparable<ReferenceSlot> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("ReferenceSlot");

  private static final org.apache.thrift.protocol.TField NAME_FIELD_DESC = new org.apache.thrift.protocol.TField("name", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField IS_SET_FIELD_DESC = new org.apache.thrift.protocol.TField("isSet", org.apache.thrift.protocol.TType.BOOL, (short)2);
  private static final org.apache.thrift.protocol.TField IDS_FIELD_DESC = new org.apache.thrift.protocol.TField("ids", org.apache.thrift.protocol.TType.LIST, (short)3);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new ReferenceSlotStandardSchemeFactory());
    schemes.put(TupleScheme.class, new ReferenceSlotTupleSchemeFactory());
  }

  public String name; // required
  public boolean isSet; // required
  public List<String> ids; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    NAME((short)1, "name"),
    IS_SET((short)2, "isSet"),
    IDS((short)3, "ids");

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
        case 1: // NAME
          return NAME;
        case 2: // IS_SET
          return IS_SET;
        case 3: // IDS
          return IDS;
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

  // isset id assignments
  private static final int __ISSET_ISSET_ID = 0;
  private byte __isset_bitfield = 0;
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.NAME, new org.apache.thrift.meta_data.FieldMetaData("name", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.IS_SET, new org.apache.thrift.meta_data.FieldMetaData("isSet", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.BOOL)));
    tmpMap.put(_Fields.IDS, new org.apache.thrift.meta_data.FieldMetaData("ids", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING))));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(ReferenceSlot.class, metaDataMap);
  }

  public ReferenceSlot() {
  }

  public ReferenceSlot(
    String name,
    boolean isSet,
    List<String> ids)
  {
    this();
    this.name = name;
    this.isSet = isSet;
    setIsSetIsSet(true);
    this.ids = ids;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public ReferenceSlot(ReferenceSlot other) {
    __isset_bitfield = other.__isset_bitfield;
    if (other.isSetName()) {
      this.name = other.name;
    }
    this.isSet = other.isSet;
    if (other.isSetIds()) {
      List<String> __this__ids = new ArrayList<String>(other.ids);
      this.ids = __this__ids;
    }
  }

  public ReferenceSlot deepCopy() {
    return new ReferenceSlot(this);
  }

  @Override
  public void clear() {
    this.name = null;
    setIsSetIsSet(false);
    this.isSet = false;
    this.ids = null;
  }

  public String getName() {
    return this.name;
  }

  public ReferenceSlot setName(String name) {
    this.name = name;
    return this;
  }

  public void unsetName() {
    this.name = null;
  }

  /** Returns true if field name is set (has been assigned a value) and false otherwise */
  public boolean isSetName() {
    return this.name != null;
  }

  public void setNameIsSet(boolean value) {
    if (!value) {
      this.name = null;
    }
  }

  public boolean isIsSet() {
    return this.isSet;
  }

  public ReferenceSlot setIsSet(boolean isSet) {
    this.isSet = isSet;
    setIsSetIsSet(true);
    return this;
  }

  public void unsetIsSet() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __ISSET_ISSET_ID);
  }

  /** Returns true if field isSet is set (has been assigned a value) and false otherwise */
  public boolean isSetIsSet() {
    return EncodingUtils.testBit(__isset_bitfield, __ISSET_ISSET_ID);
  }

  public void setIsSetIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __ISSET_ISSET_ID, value);
  }

  public int getIdsSize() {
    return (this.ids == null) ? 0 : this.ids.size();
  }

  public java.util.Iterator<String> getIdsIterator() {
    return (this.ids == null) ? null : this.ids.iterator();
  }

  public void addToIds(String elem) {
    if (this.ids == null) {
      this.ids = new ArrayList<String>();
    }
    this.ids.add(elem);
  }

  public List<String> getIds() {
    return this.ids;
  }

  public ReferenceSlot setIds(List<String> ids) {
    this.ids = ids;
    return this;
  }

  public void unsetIds() {
    this.ids = null;
  }

  /** Returns true if field ids is set (has been assigned a value) and false otherwise */
  public boolean isSetIds() {
    return this.ids != null;
  }

  public void setIdsIsSet(boolean value) {
    if (!value) {
      this.ids = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case NAME:
      if (value == null) {
        unsetName();
      } else {
        setName((String)value);
      }
      break;

    case IS_SET:
      if (value == null) {
        unsetIsSet();
      } else {
        setIsSet((Boolean)value);
      }
      break;

    case IDS:
      if (value == null) {
        unsetIds();
      } else {
        setIds((List<String>)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case NAME:
      return getName();

    case IS_SET:
      return Boolean.valueOf(isIsSet());

    case IDS:
      return getIds();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case NAME:
      return isSetName();
    case IS_SET:
      return isSetIsSet();
    case IDS:
      return isSetIds();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof ReferenceSlot)
      return this.equals((ReferenceSlot)that);
    return false;
  }

  public boolean equals(ReferenceSlot that) {
    if (that == null)
      return false;

    boolean this_present_name = true && this.isSetName();
    boolean that_present_name = true && that.isSetName();
    if (this_present_name || that_present_name) {
      if (!(this_present_name && that_present_name))
        return false;
      if (!this.name.equals(that.name))
        return false;
    }

    boolean this_present_isSet = true;
    boolean that_present_isSet = true;
    if (this_present_isSet || that_present_isSet) {
      if (!(this_present_isSet && that_present_isSet))
        return false;
      if (this.isSet != that.isSet)
        return false;
    }

    boolean this_present_ids = true && this.isSetIds();
    boolean that_present_ids = true && that.isSetIds();
    if (this_present_ids || that_present_ids) {
      if (!(this_present_ids && that_present_ids))
        return false;
      if (!this.ids.equals(that.ids))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_name = true && (isSetName());
    list.add(present_name);
    if (present_name)
      list.add(name);

    boolean present_isSet = true;
    list.add(present_isSet);
    if (present_isSet)
      list.add(isSet);

    boolean present_ids = true && (isSetIds());
    list.add(present_ids);
    if (present_ids)
      list.add(ids);

    return list.hashCode();
  }

  @Override
  public int compareTo(ReferenceSlot other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetName()).compareTo(other.isSetName());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetName()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.name, other.name);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetIsSet()).compareTo(other.isSetIsSet());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetIsSet()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.isSet, other.isSet);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetIds()).compareTo(other.isSetIds());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetIds()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.ids, other.ids);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    schemes.get(iprot.getScheme()).getScheme().read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    schemes.get(oprot.getScheme()).getScheme().write(oprot, this);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("ReferenceSlot(");
    boolean first = true;

    sb.append("name:");
    if (this.name == null) {
      sb.append("null");
    } else {
      sb.append(this.name);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("isSet:");
    sb.append(this.isSet);
    first = false;
    if (!first) sb.append(", ");
    sb.append("ids:");
    if (this.ids == null) {
      sb.append("null");
    } else {
      sb.append(this.ids);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    if (name == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'name' was not present! Struct: " + toString());
    }
    // alas, we cannot check 'isSet' because it's a primitive and you chose the non-beans generator.
    if (ids == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'ids' was not present! Struct: " + toString());
    }
    // check for sub-struct validity
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
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class ReferenceSlotStandardSchemeFactory implements SchemeFactory {
    public ReferenceSlotStandardScheme getScheme() {
      return new ReferenceSlotStandardScheme();
    }
  }

  private static class ReferenceSlotStandardScheme extends StandardScheme<ReferenceSlot> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, ReferenceSlot struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // NAME
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.name = iprot.readString();
              struct.setNameIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // IS_SET
            if (schemeField.type == org.apache.thrift.protocol.TType.BOOL) {
              struct.isSet = iprot.readBool();
              struct.setIsSetIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // IDS
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list8 = iprot.readListBegin();
                struct.ids = new ArrayList<String>(_list8.size);
                String _elem9;
                for (int _i10 = 0; _i10 < _list8.size; ++_i10)
                {
                  _elem9 = iprot.readString();
                  struct.ids.add(_elem9);
                }
                iprot.readListEnd();
              }
              struct.setIdsIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      if (!struct.isSetIsSet()) {
        throw new org.apache.thrift.protocol.TProtocolException("Required field 'isSet' was not found in serialized data! Struct: " + toString());
      }
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, ReferenceSlot struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.name != null) {
        oprot.writeFieldBegin(NAME_FIELD_DESC);
        oprot.writeString(struct.name);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(IS_SET_FIELD_DESC);
      oprot.writeBool(struct.isSet);
      oprot.writeFieldEnd();
      if (struct.ids != null) {
        oprot.writeFieldBegin(IDS_FIELD_DESC);
        {
          oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRING, struct.ids.size()));
          for (String _iter11 : struct.ids)
          {
            oprot.writeString(_iter11);
          }
          oprot.writeListEnd();
        }
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class ReferenceSlotTupleSchemeFactory implements SchemeFactory {
    public ReferenceSlotTupleScheme getScheme() {
      return new ReferenceSlotTupleScheme();
    }
  }

  private static class ReferenceSlotTupleScheme extends TupleScheme<ReferenceSlot> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, ReferenceSlot struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      oprot.writeString(struct.name);
      oprot.writeBool(struct.isSet);
      {
        oprot.writeI32(struct.ids.size());
        for (String _iter12 : struct.ids)
        {
          oprot.writeString(_iter12);
        }
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, ReferenceSlot struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      struct.name = iprot.readString();
      struct.setNameIsSet(true);
      struct.isSet = iprot.readBool();
      struct.setIsSetIsSet(true);
      {
        org.apache.thrift.protocol.TList _list13 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRING, iprot.readI32());
        struct.ids = new ArrayList<String>(_list13.size);
        String _elem14;
        for (int _i15 = 0; _i15 < _list13.size; ++_i15)
        {
          _elem14 = iprot.readString();
          struct.ids.add(_elem14);
        }
      }
      struct.setIdsIsSet(true);
    }
  }

}
