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
@Generated(value = "Autogenerated by Thrift Compiler (0.9.2)", date = "2015-7-30")
public class InvalidModelSpec extends TException implements org.apache.thrift.TBase<InvalidModelSpec, InvalidModelSpec._Fields>, java.io.Serializable, Cloneable, Comparable<InvalidModelSpec> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("InvalidModelSpec");

  private static final org.apache.thrift.protocol.TField SPEC_FIELD_DESC = new org.apache.thrift.protocol.TField("spec", org.apache.thrift.protocol.TType.STRUCT, (short)1);
  private static final org.apache.thrift.protocol.TField REASON_FIELD_DESC = new org.apache.thrift.protocol.TField("reason", org.apache.thrift.protocol.TType.STRING, (short)2);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new InvalidModelSpecStandardSchemeFactory());
    schemes.put(TupleScheme.class, new InvalidModelSpecTupleSchemeFactory());
  }

  public ModelSpec spec; // required
  public String reason; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    SPEC((short)1, "spec"),
    REASON((short)2, "reason");

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
        case 1: // SPEC
          return SPEC;
        case 2: // REASON
          return REASON;
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
    tmpMap.put(_Fields.SPEC, new org.apache.thrift.meta_data.FieldMetaData("spec", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, ModelSpec.class)));
    tmpMap.put(_Fields.REASON, new org.apache.thrift.meta_data.FieldMetaData("reason", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(InvalidModelSpec.class, metaDataMap);
  }

  public InvalidModelSpec() {
  }

  public InvalidModelSpec(
    ModelSpec spec,
    String reason)
  {
    this();
    this.spec = spec;
    this.reason = reason;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public InvalidModelSpec(InvalidModelSpec other) {
    if (other.isSetSpec()) {
      this.spec = new ModelSpec(other.spec);
    }
    if (other.isSetReason()) {
      this.reason = other.reason;
    }
  }

  public InvalidModelSpec deepCopy() {
    return new InvalidModelSpec(this);
  }

  @Override
  public void clear() {
    this.spec = null;
    this.reason = null;
  }

  public ModelSpec getSpec() {
    return this.spec;
  }

  public InvalidModelSpec setSpec(ModelSpec spec) {
    this.spec = spec;
    return this;
  }

  public void unsetSpec() {
    this.spec = null;
  }

  /** Returns true if field spec is set (has been assigned a value) and false otherwise */
  public boolean isSetSpec() {
    return this.spec != null;
  }

  public void setSpecIsSet(boolean value) {
    if (!value) {
      this.spec = null;
    }
  }

  public String getReason() {
    return this.reason;
  }

  public InvalidModelSpec setReason(String reason) {
    this.reason = reason;
    return this;
  }

  public void unsetReason() {
    this.reason = null;
  }

  /** Returns true if field reason is set (has been assigned a value) and false otherwise */
  public boolean isSetReason() {
    return this.reason != null;
  }

  public void setReasonIsSet(boolean value) {
    if (!value) {
      this.reason = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case SPEC:
      if (value == null) {
        unsetSpec();
      } else {
        setSpec((ModelSpec)value);
      }
      break;

    case REASON:
      if (value == null) {
        unsetReason();
      } else {
        setReason((String)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case SPEC:
      return getSpec();

    case REASON:
      return getReason();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case SPEC:
      return isSetSpec();
    case REASON:
      return isSetReason();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof InvalidModelSpec)
      return this.equals((InvalidModelSpec)that);
    return false;
  }

  public boolean equals(InvalidModelSpec that) {
    if (that == null)
      return false;

    boolean this_present_spec = true && this.isSetSpec();
    boolean that_present_spec = true && that.isSetSpec();
    if (this_present_spec || that_present_spec) {
      if (!(this_present_spec && that_present_spec))
        return false;
      if (!this.spec.equals(that.spec))
        return false;
    }

    boolean this_present_reason = true && this.isSetReason();
    boolean that_present_reason = true && that.isSetReason();
    if (this_present_reason || that_present_reason) {
      if (!(this_present_reason && that_present_reason))
        return false;
      if (!this.reason.equals(that.reason))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_spec = true && (isSetSpec());
    list.add(present_spec);
    if (present_spec)
      list.add(spec);

    boolean present_reason = true && (isSetReason());
    list.add(present_reason);
    if (present_reason)
      list.add(reason);

    return list.hashCode();
  }

  @Override
  public int compareTo(InvalidModelSpec other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetSpec()).compareTo(other.isSetSpec());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetSpec()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.spec, other.spec);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetReason()).compareTo(other.isSetReason());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetReason()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.reason, other.reason);
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
    StringBuilder sb = new StringBuilder("InvalidModelSpec(");
    boolean first = true;

    sb.append("spec:");
    if (this.spec == null) {
      sb.append("null");
    } else {
      sb.append(this.spec);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("reason:");
    if (this.reason == null) {
      sb.append("null");
    } else {
      sb.append(this.reason);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    if (spec == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'spec' was not present! Struct: " + toString());
    }
    if (reason == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'reason' was not present! Struct: " + toString());
    }
    // check for sub-struct validity
    if (spec != null) {
      spec.validate();
    }
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

  private static class InvalidModelSpecStandardSchemeFactory implements SchemeFactory {
    public InvalidModelSpecStandardScheme getScheme() {
      return new InvalidModelSpecStandardScheme();
    }
  }

  private static class InvalidModelSpecStandardScheme extends StandardScheme<InvalidModelSpec> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, InvalidModelSpec struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // SPEC
            if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
              struct.spec = new ModelSpec();
              struct.spec.read(iprot);
              struct.setSpecIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // REASON
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.reason = iprot.readString();
              struct.setReasonIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, InvalidModelSpec struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.spec != null) {
        oprot.writeFieldBegin(SPEC_FIELD_DESC);
        struct.spec.write(oprot);
        oprot.writeFieldEnd();
      }
      if (struct.reason != null) {
        oprot.writeFieldBegin(REASON_FIELD_DESC);
        oprot.writeString(struct.reason);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class InvalidModelSpecTupleSchemeFactory implements SchemeFactory {
    public InvalidModelSpecTupleScheme getScheme() {
      return new InvalidModelSpecTupleScheme();
    }
  }

  private static class InvalidModelSpecTupleScheme extends TupleScheme<InvalidModelSpec> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, InvalidModelSpec struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      struct.spec.write(oprot);
      oprot.writeString(struct.reason);
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, InvalidModelSpec struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      struct.spec = new ModelSpec();
      struct.spec.read(iprot);
      struct.setSpecIsSet(true);
      struct.reason = iprot.readString();
      struct.setReasonIsSet(true);
    }
  }

}

