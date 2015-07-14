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
@Generated(value = "Autogenerated by Thrift Compiler (0.9.2)", date = "2015-7-14")
public class CollaborationQueryInvocationSpecification implements org.apache.thrift.TBase<CollaborationQueryInvocationSpecification, CollaborationQueryInvocationSpecification._Fields>, java.io.Serializable, Cloneable, Comparable<CollaborationQueryInvocationSpecification> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("CollaborationQueryInvocationSpecification");

  private static final org.apache.thrift.protocol.TField PATTERN_FQN_FIELD_DESC = new org.apache.thrift.protocol.TField("patternFQN", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField BINDINGS_FIELD_DESC = new org.apache.thrift.protocol.TField("bindings", org.apache.thrift.protocol.TType.LIST, (short)2);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new CollaborationQueryInvocationSpecificationStandardSchemeFactory());
    schemes.put(TupleScheme.class, new CollaborationQueryInvocationSpecificationTupleSchemeFactory());
  }

  public String patternFQN; // required
  public List<CollaborationQueryBinding> bindings; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    PATTERN_FQN((short)1, "patternFQN"),
    BINDINGS((short)2, "bindings");

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
        case 1: // PATTERN_FQN
          return PATTERN_FQN;
        case 2: // BINDINGS
          return BINDINGS;
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
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.PATTERN_FQN, new org.apache.thrift.meta_data.FieldMetaData("patternFQN", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.BINDINGS, new org.apache.thrift.meta_data.FieldMetaData("bindings", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, CollaborationQueryBinding.class))));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(CollaborationQueryInvocationSpecification.class, metaDataMap);
  }

  public CollaborationQueryInvocationSpecification() {
  }

  public CollaborationQueryInvocationSpecification(
    String patternFQN,
    List<CollaborationQueryBinding> bindings)
  {
    this();
    this.patternFQN = patternFQN;
    this.bindings = bindings;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public CollaborationQueryInvocationSpecification(CollaborationQueryInvocationSpecification other) {
    if (other.isSetPatternFQN()) {
      this.patternFQN = other.patternFQN;
    }
    if (other.isSetBindings()) {
      List<CollaborationQueryBinding> __this__bindings = new ArrayList<CollaborationQueryBinding>(other.bindings.size());
      for (CollaborationQueryBinding other_element : other.bindings) {
        __this__bindings.add(new CollaborationQueryBinding(other_element));
      }
      this.bindings = __this__bindings;
    }
  }

  public CollaborationQueryInvocationSpecification deepCopy() {
    return new CollaborationQueryInvocationSpecification(this);
  }

  @Override
  public void clear() {
    this.patternFQN = null;
    this.bindings = null;
  }

  public String getPatternFQN() {
    return this.patternFQN;
  }

  public CollaborationQueryInvocationSpecification setPatternFQN(String patternFQN) {
    this.patternFQN = patternFQN;
    return this;
  }

  public void unsetPatternFQN() {
    this.patternFQN = null;
  }

  /** Returns true if field patternFQN is set (has been assigned a value) and false otherwise */
  public boolean isSetPatternFQN() {
    return this.patternFQN != null;
  }

  public void setPatternFQNIsSet(boolean value) {
    if (!value) {
      this.patternFQN = null;
    }
  }

  public int getBindingsSize() {
    return (this.bindings == null) ? 0 : this.bindings.size();
  }

  public java.util.Iterator<CollaborationQueryBinding> getBindingsIterator() {
    return (this.bindings == null) ? null : this.bindings.iterator();
  }

  public void addToBindings(CollaborationQueryBinding elem) {
    if (this.bindings == null) {
      this.bindings = new ArrayList<CollaborationQueryBinding>();
    }
    this.bindings.add(elem);
  }

  public List<CollaborationQueryBinding> getBindings() {
    return this.bindings;
  }

  public CollaborationQueryInvocationSpecification setBindings(List<CollaborationQueryBinding> bindings) {
    this.bindings = bindings;
    return this;
  }

  public void unsetBindings() {
    this.bindings = null;
  }

  /** Returns true if field bindings is set (has been assigned a value) and false otherwise */
  public boolean isSetBindings() {
    return this.bindings != null;
  }

  public void setBindingsIsSet(boolean value) {
    if (!value) {
      this.bindings = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case PATTERN_FQN:
      if (value == null) {
        unsetPatternFQN();
      } else {
        setPatternFQN((String)value);
      }
      break;

    case BINDINGS:
      if (value == null) {
        unsetBindings();
      } else {
        setBindings((List<CollaborationQueryBinding>)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case PATTERN_FQN:
      return getPatternFQN();

    case BINDINGS:
      return getBindings();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case PATTERN_FQN:
      return isSetPatternFQN();
    case BINDINGS:
      return isSetBindings();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof CollaborationQueryInvocationSpecification)
      return this.equals((CollaborationQueryInvocationSpecification)that);
    return false;
  }

  public boolean equals(CollaborationQueryInvocationSpecification that) {
    if (that == null)
      return false;

    boolean this_present_patternFQN = true && this.isSetPatternFQN();
    boolean that_present_patternFQN = true && that.isSetPatternFQN();
    if (this_present_patternFQN || that_present_patternFQN) {
      if (!(this_present_patternFQN && that_present_patternFQN))
        return false;
      if (!this.patternFQN.equals(that.patternFQN))
        return false;
    }

    boolean this_present_bindings = true && this.isSetBindings();
    boolean that_present_bindings = true && that.isSetBindings();
    if (this_present_bindings || that_present_bindings) {
      if (!(this_present_bindings && that_present_bindings))
        return false;
      if (!this.bindings.equals(that.bindings))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_patternFQN = true && (isSetPatternFQN());
    list.add(present_patternFQN);
    if (present_patternFQN)
      list.add(patternFQN);

    boolean present_bindings = true && (isSetBindings());
    list.add(present_bindings);
    if (present_bindings)
      list.add(bindings);

    return list.hashCode();
  }

  @Override
  public int compareTo(CollaborationQueryInvocationSpecification other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetPatternFQN()).compareTo(other.isSetPatternFQN());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetPatternFQN()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.patternFQN, other.patternFQN);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetBindings()).compareTo(other.isSetBindings());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetBindings()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.bindings, other.bindings);
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
    StringBuilder sb = new StringBuilder("CollaborationQueryInvocationSpecification(");
    boolean first = true;

    sb.append("patternFQN:");
    if (this.patternFQN == null) {
      sb.append("null");
    } else {
      sb.append(this.patternFQN);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("bindings:");
    if (this.bindings == null) {
      sb.append("null");
    } else {
      sb.append(this.bindings);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    if (patternFQN == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'patternFQN' was not present! Struct: " + toString());
    }
    if (bindings == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'bindings' was not present! Struct: " + toString());
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
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class CollaborationQueryInvocationSpecificationStandardSchemeFactory implements SchemeFactory {
    public CollaborationQueryInvocationSpecificationStandardScheme getScheme() {
      return new CollaborationQueryInvocationSpecificationStandardScheme();
    }
  }

  private static class CollaborationQueryInvocationSpecificationStandardScheme extends StandardScheme<CollaborationQueryInvocationSpecification> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, CollaborationQueryInvocationSpecification struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // PATTERN_FQN
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.patternFQN = iprot.readString();
              struct.setPatternFQNIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // BINDINGS
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list16 = iprot.readListBegin();
                struct.bindings = new ArrayList<CollaborationQueryBinding>(_list16.size);
                CollaborationQueryBinding _elem17;
                for (int _i18 = 0; _i18 < _list16.size; ++_i18)
                {
                  _elem17 = new CollaborationQueryBinding();
                  _elem17.read(iprot);
                  struct.bindings.add(_elem17);
                }
                iprot.readListEnd();
              }
              struct.setBindingsIsSet(true);
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
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, CollaborationQueryInvocationSpecification struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.patternFQN != null) {
        oprot.writeFieldBegin(PATTERN_FQN_FIELD_DESC);
        oprot.writeString(struct.patternFQN);
        oprot.writeFieldEnd();
      }
      if (struct.bindings != null) {
        oprot.writeFieldBegin(BINDINGS_FIELD_DESC);
        {
          oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, struct.bindings.size()));
          for (CollaborationQueryBinding _iter19 : struct.bindings)
          {
            _iter19.write(oprot);
          }
          oprot.writeListEnd();
        }
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class CollaborationQueryInvocationSpecificationTupleSchemeFactory implements SchemeFactory {
    public CollaborationQueryInvocationSpecificationTupleScheme getScheme() {
      return new CollaborationQueryInvocationSpecificationTupleScheme();
    }
  }

  private static class CollaborationQueryInvocationSpecificationTupleScheme extends TupleScheme<CollaborationQueryInvocationSpecification> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, CollaborationQueryInvocationSpecification struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      oprot.writeString(struct.patternFQN);
      {
        oprot.writeI32(struct.bindings.size());
        for (CollaborationQueryBinding _iter20 : struct.bindings)
        {
          _iter20.write(oprot);
        }
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, CollaborationQueryInvocationSpecification struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      struct.patternFQN = iprot.readString();
      struct.setPatternFQNIsSet(true);
      {
        org.apache.thrift.protocol.TList _list21 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, iprot.readI32());
        struct.bindings = new ArrayList<CollaborationQueryBinding>(_list21.size);
        CollaborationQueryBinding _elem22;
        for (int _i23 = 0; _i23 < _list21.size; ++_i23)
        {
          _elem22 = new CollaborationQueryBinding();
          _elem22.read(iprot);
          struct.bindings.add(_elem22);
        }
      }
      struct.setBindingsIsSet(true);
    }
  }

}

