/**
 * Employees API
 * API specification for employees.
 *
 * The version of the OpenAPI document: 1.0.0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


export interface ErrorResponse { 
    /**
     * The response code. Will always have 500 as a value indicating that the server has encountered an internal error.
     */
    responseCode: number;
    /**
     * Will always have *Error* as a value indicating that the server has encountered an internal error. For example, if the database cannot be reached.
     */
    status: string;
    /**
     * This field will always contain the same message \'An internal error has occurred\'.
     */
    reason: string;
}
