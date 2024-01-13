import { Request, Response, NextFunction } from "express";

const healthCheck = async (req: Request, res: Response, next: NextFunction) => {
  try {
    return res.status(200).json({
      message: "Server is responding...",
      data: req.body
    });
  } catch (error) {
    return res.status(400).json({
        message: "Server is not responding...",
      });
  }
};



export {
    healthCheck
}

