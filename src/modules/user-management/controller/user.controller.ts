import { Request, Response } from "express";
import { IUsers } from "../model/user.model";
import { uservalidation, loginValidation } from "../validator/user.validator";
import { badRequestError } from "../../../middleware/errorResponse";
import { Users } from "../model/user.model";
import { successResponse } from "@agnimura/common";
import { randomBytes, randomInt } from "crypto";
import { Password } from "../../../utils/password";
import { loginReq } from "../model/user.interface";
import { createToken, CustomRequest } from "../../../middleware/auth/auth";
import { ITokenData } from "../../../middleware/auth/auth.interface";

const registration = async (req: Request, res: Response) => {
  try {
    const data: any = req as CustomRequest;
    const role = data.token.role;
    console.log(role);
    if (role !== "admin")
      return badRequestError("dont have enough access", {}, res);
    const validateData: IUsers = req.body;
    console.log("valifddt", validateData);
    const details = await Users.findOne({ email: validateData.email });
    if (details) return badRequestError("Email already in use", {}, res);
    const validate: any = uservalidation(validateData);
    console.log("validate", validate);
    if (validate?.error?.details?.length > 0)
      return badRequestError(
        "somefields are missing!",
        validate?.error?.details,
        res
      );
    const passwordGeneration = randomBytes(4).toString("hex");
    console.log(passwordGeneration);
    const password: string = await Password.toHash(passwordGeneration);
    validateData.password = password;
    validateData.kitchenName =
      validateData.role === "kitchen" ? validateData.kitchenName : "NA";
    console.log("data", validateData);
    const respData = new Users(validateData);
    await respData.save();
    return successResponse(201, "data saved successfully", respData, res);
  } catch (err: any) {
    return badRequestError("failed to save database", err, res);
  }
};

const login = async (req: Request, res: Response) => {
  try {
    const obj: loginReq = req.body;
    const validate: any = loginValidation(obj);
    if (validate?.error?.details?.length > 0)
      return badRequestError(
        "somefields are missing!",
        validate?.error?.details,
        res
      );
    const details: any = await Users.findOne({ email: obj.email });
    console.log(details);
    if (!details) return badRequestError("Email Not found", details, res);
    const passwordCompare = await Password.compare(
      details.password,
      obj.password
    );
    if (passwordCompare && details.status) {
      const tokenData: ITokenData = {
        id: details.id,
        email: details.email,
        role: details.role,
      };
      const token = await createToken(tokenData);
      const resObj: { email: string; role: string; token: string } = {
        email: details.email,
        role: details.role,
        token: token,
      };
      return successResponse(200, "logged in successfully", resObj, res);
    } else {
      return badRequestError("Password mismatch!", {}, res);
    }
  } catch (err) {
    return badRequestError("Something went wrong!", err, res);
  }
};

const getAllUsersController = async (req: Request, res: Response) => {
  try {
    const type: string = (req.query.type as string) || "";
    console.log(type);
    const data = await Users.find(type ? { role: type } : {});
    console.log(data);
    return successResponse(200, "users list fetched successfully0", data, res);
  } catch (err) {
    return badRequestError("Something went wrong!", err, res);
  }
};

const getUserByIdController = async (req: Request, res: Response) => {
  try {
    const id: string = req.params.userId;
    const details = await Users.findById({ _id: id });
    if (!details) return badRequestError("User not found", {}, res);
    return successResponse(200, "User details", details, res);
  } catch (err) {
    return badRequestError("Something went wrong!", err, res);
  }
};

const updateUserByIdController = async (req: Request, res: Response) => {
  try {
    const data: any = req as CustomRequest;
    const id: string = req.params.userId;
    const role = data.token.role;
    const reqData = req.body;
    const details = await Users.findById({ _id: id });
    if (!details) return badRequestError("User not found", {}, res);
    if (role !== "admin")
      return badRequestError("dont have enough access", {}, res);
    const updateUser = await Users.findByIdAndUpdate(
      { _id: id },
      { $set: reqData }
    );
    return successResponse(
      200,
      "User is successfully updated",
      updateUser,
      res
    );
  } catch (err) {
    return badRequestError("Something went wrong!", err, res);
  }
};

const deleteUserByIdController = async (req: Request, res: Response) => {
  try {
    const data: any = req as CustomRequest;
    const id: string = req.params.userId;
    const role: string = data.token.role;
    const reqData = req.body;
    const details = await Users.findById({ _id: id });
    if (!details) return badRequestError("User not found", {}, res);
    if (role !== "admin")
      return badRequestError("dont have enough access", {}, res);
    const delteUser = await Users.deleteOne({ _id: id });
    return successResponse(200, "User is successfully deleted", delteUser, res);
  } catch (err) {
    return badRequestError("Something went wrong!", err, res);
  }
};

const resetUserPasswordController = async (req: Request, res: Response) => {
  try {
    const data: any = req as CustomRequest;
    const role: string = data.token.role;
    const { email }: { email: string } = req.body;
    if (role !== "admin")
      return badRequestError("dont have enough access", {}, res);
    const details = await Users.findOne({ email: email });
    if (!details) return badRequestError("User not found", {}, res);
    const passwordGeneration = randomBytes(4).toString("hex");
    console.log(passwordGeneration);
    const password = await Password.toHash(passwordGeneration);
    const updateUser = await Users.findByIdAndUpdate(
      { _id: details.id },
      {
        $set: {
          password: password,
        },
      }
    );
    return successResponse(200, "User is successfully deleted", updateUser, res);
  } catch (err) {
    return badRequestError("Something went wrong!", err, res);
  }
};




//fetch all seller wrt isacive show item



//create order from user


//fetch all orders category for admin

export {
  registration,
  login,
  getAllUsersController,
  getUserByIdController,
  updateUserByIdController,
  deleteUserByIdController,
  resetUserPasswordController,
};

