import { Response } from "express";

export function badRequestError(message: string, err: any, res: Response) {
  let status = 400;

  res.status(status).send({
    message: message,
    data: err,
  });
}

export function genericError(res: Response) {
  let status = 400;
  res.status(status).send({
    message: "Something went wrogn!",
  });
}

export function notFoundError(res: Response) {
  let status = 404;
  res.status(status).send({
    message: "Not Found",
  });
}

export function notAuthorizedError(res: Response) {
  let status = 401;
  res.status(status).send({
    message: "Not Authorized",
  });
}

export function databaseError(message: string, res: Response) {
  const status = 500;
  res.status(status).send({
    message,
  });
}
